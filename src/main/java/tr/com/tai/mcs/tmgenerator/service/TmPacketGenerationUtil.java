package tr.com.tai.mcs.tmgenerator.service;

import lombok.extern.slf4j.Slf4j;
import tr.com.tai.mcs.tmgenerator.data.Criteria;
import tr.com.tai.mcs.tmgenerator.data.TelemetryPacket;
import tr.com.tai.mcs.tmgenerator.data.TelemetryParameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class TmPacketGenerationUtil {
    private static final String SATELLITE = "GK-1";
    private static final int MAX_PARAM_VALUE = 100;
    private static final int OFFSET = 10;
    private static final int MIN_PARAM_VALUE = 0;

    public static TelemetryPacket generateTm(String name, int numberOfParameter) {
        TelemetryPacket packet = new TelemetryPacket(generateUUID(), name, SATELLITE, System.currentTimeMillis(),
                System.currentTimeMillis(), "TM", "TM");
        Map<String, TelemetryParameter> parameterMap = new HashMap<>();
        for (int i = 0; i < numberOfParameter; i++) {
            TelemetryParameter parameter = generateParameter(name, (i + 1));
            parameterMap.put(parameter.getName(), parameter);
            packet.setParameterMap(parameterMap);
        }
        return packet;
    }

    private static TelemetryParameter generateParameter(String packetName, int parameterIdIncrement) {
        TelemetryParameter parameter = new TelemetryParameter(generateUUID(), generateParameterName(packetName, parameterIdIncrement),
                SATELLITE, System.currentTimeMillis(), System.currentTimeMillis());
        try {

            Random random = new Random();
            int v1 = random.nextInt(MAX_PARAM_VALUE - OFFSET) + OFFSET;
            int v2 = random.nextInt((v1 - OFFSET) > 0 ? (v1 - OFFSET) : 1) + OFFSET;
            int v3 = random.nextInt((v2 - MIN_PARAM_VALUE) > 0 ? v2 - MIN_PARAM_VALUE : 1) + MIN_PARAM_VALUE;
            int v4 = random.nextInt((v3 - MIN_PARAM_VALUE) > 0 ? v3 - MIN_PARAM_VALUE : 1) + MIN_PARAM_VALUE;
            parameter.setIncludeCondition(new Criteria(v4, v3, v2, v1));
            parameter.setValue(random.nextInt(MAX_PARAM_VALUE - MIN_PARAM_VALUE) + "");
        } catch (Exception ex) {
            log.error("Error is occurred while generating parameter randomly. ");
        }
        return parameter;
    }

    private static String generateParameterName(String packetName, int incrementValue) {
        try {
            StringBuilder builder = new StringBuilder();
            String[] sp = packetName.split("_");
            for (int i = 0; i < sp.length - 1; i++) {
                builder.append(sp[i] + "_");
            }
            String lastIndex = sp[(sp.length - 1)];
            Integer index = Integer.valueOf(lastIndex);
            builder.append("000" + (index + incrementValue));
            return builder.toString();
        } catch (Exception ex) {
            log.error("generateParameterName failed on packet: {}", packetName);
            return packetName;
        }
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
