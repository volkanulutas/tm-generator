package tr.com.tai.mcs.tmgenerator.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class TelemetryParameter implements Serializable {
    private String id;
    private String name;
    private String spaceSystem;
    private Criteria includeCondition;
    private String value;
    private long receptionTimeStamp;
    private long generationTimeStamp;
    private boolean validity;

    public TelemetryParameter(String id, String name, String spaceSystem, long receptionTimeStamp, long generationTimeStamp) {
        this.id = id;
        this.name = name;
        this.spaceSystem = spaceSystem;
        this.receptionTimeStamp = receptionTimeStamp;
        this.generationTimeStamp = generationTimeStamp;
    }
}
