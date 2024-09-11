package apps.amaralus.orch.core;

import apps.amaralus.orch.core.common.SedaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SedaProperties.class)
public class OrchCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchCoreApplication.class, args);
    }

}
