package tr.com.tai.mcs.tmgenerator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:tm.properties")
@ConfigurationProperties(value = "tm")
public class TmConfig {
    private List<String> tmList = new ArrayList<>();

    public List<String> getTmList() {
        return tmList;
    }
}
