package tr.com.tai.mcs.tmgenerator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tr.com.tai.mcs.tmgenerator.data.TelemetryPacket;
import tr.com.tai.mcs.tmgenerator.repository.TelemetryPacketRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/tm")
public class TelemetryController {

    @Autowired
    private TelemetryPacketRepository repository;

    @GetMapping(value = "/getByName")
    public TelemetryPacket getTelemetry(@RequestParam String name) {
        return repository.findByName(name);
    }

    @GetMapping(value = "/getBetween")
    public List<TelemetryPacket> getTelemetry(@RequestParam long startTime, @RequestParam long endTime) {
        //start : 1602556801431  end : 1602556810631
        List<TelemetryPacket> telemetryPacketList = repository.findByGenerationTimeStampGreaterThanEqualAndGenerationTimeStampLessThanEqual(startTime, endTime);
        System.out.println(telemetryPacketList != null ? telemetryPacketList.size() : "0");
        return telemetryPacketList;
    }

}
