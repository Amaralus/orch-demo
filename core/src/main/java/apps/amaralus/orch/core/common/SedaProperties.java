package apps.amaralus.orch.core.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("seda")
@Getter
@Slf4j
public class SedaProperties {
    private Map<String, Integer> endpointsThreads;

    public void setEndpointsThreads(Map<String, Integer> endpointsThreads) {
        endpointsThreads.forEach((endpoint, threads) -> {
            if (threads < 1)
                throw new IllegalArgumentException("Number of threads for the endpoint \"" + endpoint + "\" must be greater then 0!");
        });
        this.endpointsThreads = endpointsThreads;
    }
}
