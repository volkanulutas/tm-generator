package tr.com.tai.mcs.tmgenerator.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EventPublisherService {

    @Autowired
    private AmqpTemplate template;

    public void publishEvent(Events event, Object messages) {
        try {
            Map<String, Object> message = new HashMap<String, Object>();
            message.put("event", event);
            message.put("message", messages);
            template.convertAndSend(Constants.queueName, message);
        } catch (Exception ex) {
            log.error("Error is occurred while sending MQ message on queue: {}", Constants.queueName);
        }
    }
}
