package tr.com.tai.mcs.tmgenerator.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Document(indexName = "telemetry", type = "telemetryPacket")
public class TelemetryPacket implements Serializable {

    @Id
    private String id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String spaceSystem;
    private long receptionTimeStamp;
    private long generationTimeStamp;
    private Map<String, TelemetryParameter> parameterMap;

    public TelemetryPacket(String id, String name, String spaceSystem,
                           long receptionTimeStamp, long generationTimeStamp,
                           String shortDescription, String longDescription) {
        this.id = id;
        this.name = name;
        this.spaceSystem = spaceSystem;
        this.receptionTimeStamp = receptionTimeStamp;
        this.generationTimeStamp = generationTimeStamp;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }
}
