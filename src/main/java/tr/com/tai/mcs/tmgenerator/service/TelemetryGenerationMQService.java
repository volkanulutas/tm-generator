package tr.com.tai.mcs.tmgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.tai.mcs.tmgenerator.amqp.EventPublisherService;
import tr.com.tai.mcs.tmgenerator.amqp.Events;
import tr.com.tai.mcs.tmgenerator.configuration.TmConfig;
import tr.com.tai.mcs.tmgenerator.data.TelemetryPacket;
import tr.com.tai.mcs.tmgenerator.repository.TelemetryPacketRepository;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TelemetryGenerationMQService {

    @Autowired
    private EventPublisherService eventPublisherService;

    @Autowired
    private TmConfig tmConfig;

    @Autowired
    private TelemetryPacketRepository repository;

    private ScheduledExecutorService service;

    public boolean startGeneration() {
        try {
            service = Executors.newScheduledThreadPool(8);
            service.scheduleAtFixedRate(() -> {
                TelemetryPacket packet = TmPacketGenerationUtil.generateTm(getRandomTmName(), getRandomParameterLength());
                eventPublisherService.publishEvent(Events.Event1, packet);
                postToElasticSearch(packet);
            }, 0, 100L, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void postToElasticSearch(TelemetryPacket packet) {
        try {
            System.out.println("Post to elastic : " + packet.toString());
            TelemetryPacket save = repository.save(packet);
        } catch (Exception e) {
            System.err.println(e);
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

    public String getRandomTmName() {
        List<String> tmList = tmConfig.getTmList();
        Random random = new Random();
        int index = random.nextInt(tmList.size());
        return tmConfig.getTmList().get(index);
    }

    public int getRandomParameterLength() {
        Random random = new Random();
        return random.nextInt(5);
    }
}

