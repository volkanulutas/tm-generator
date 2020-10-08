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
public class Criteria implements Serializable {
    private double innerMax;
    private double innerMin;
    private double outerMin;
    private double outerMax;

    public Criteria(double innerMax, double innerMin, double outerMin, double outerMax) {
        this.innerMax = innerMax;
        this.innerMin = innerMin;
        this.outerMin = outerMin;
        this.outerMax = outerMax;
    }
}
