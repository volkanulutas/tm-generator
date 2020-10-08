package tr.com.tai.mcs.tmgenerator.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class TelemetryPacket implements Serializable {
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
