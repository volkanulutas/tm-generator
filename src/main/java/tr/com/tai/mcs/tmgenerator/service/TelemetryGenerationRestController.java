package tr.com.tai.mcs.tmgenerator.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tr.com.tai.mcs.tmgenerator.amqp.Constants;
import tr.com.tai.mcs.tmgenerator.amqp.EventHandler;
import tr.com.tai.mcs.tmgenerator.amqp.EventPublisherService;
import tr.com.tai.mcs.tmgenerator.amqp.Events;
import tr.com.tai.mcs.tmgenerator.data.TelemetryPacket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@RestController
public class TelemetryGenerationRestController {

    @Autowired
    private EventPublisherService eventPublisherService;

    @Autowired
    private TelemetryGenerationMQService telemetryGenerationMQService;

    @GetMapping(value = "/start")
    public boolean startGeneration() {
        return telemetryGenerationMQService.startGeneration();
    }

    @GetMapping(value = "/stop")
    public boolean stopGeneration() {
        return telemetryGenerationMQService.stopGeneration();
    }

    // rabbitmq test
    @GetMapping("/rabbitMq")
    public ModelAndView welcome() throws IOException, TimeoutException {

        TelemetryPacket message1 = TmPacketGenerationUtil.generateTm(telemetryGenerationMQService.getRandomTmName(),
                telemetryGenerationMQService.getRandomParameterLength());
        eventPublisherService.publishEvent(Events.Event1, message1);
        String message2 = "Implementing RabbitMQ";
        eventPublisherService.publishEvent(Events.Event2, message2);
        return new ModelAndView("index", "message", "Implemented");
    }

    // rabbitmq test
    @RabbitListener(queues = Constants.queueName)
    public void processQueue(Map<String, Object> message) {
        EventHandler.handler((Events) message.get("event"), message.get("message"));
    }
}
