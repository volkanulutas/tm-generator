package tr.com.tai.mcs.tmgenerator.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import tr.com.tai.mcs.tmgenerator.data.TelemetryPacket;

import java.util.List;

public interface TelemetryPacketRepository extends ElasticsearchRepository<TelemetryPacket, String> {

    TelemetryPacket findByName(String name);

    List<TelemetryPacket> findByGenerationTimeStampGreaterThanEqualAndGenerationTimeStampLessThanEqual(long startTime, long endTime);
}
