package tr.com.tai.mcs.tmgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.tai.mcs.tmgenerator.amqp.EventPublisherService;
import tr.com.tai.mcs.tmgenerator.amqp.Events;
import tr.com.tai.mcs.tmgenerator.data.TelemetryPacket;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class TelemetryGenerationMQService {

    @Autowired
    private EventPublisherService eventPublisherService;

    private ScheduledExecutorService service;

    public boolean startGeneration() {
        try {
            service = Executors.newScheduledThreadPool(5);
            service.scheduleAtFixedRate(() -> {
                TelemetryPacket packet = TmPacketGenerationUtil.generateTm("TM_003_012_009_0000", 3);
                eventPublisherService.publishEvent(Events.Event1, packet);
            }, 0, 1000L, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean stopGeneration() {
        try {
            service.shutdownNow();
        } catch (Exception ex) {
            return false;

        }
        return true;
    }
}

