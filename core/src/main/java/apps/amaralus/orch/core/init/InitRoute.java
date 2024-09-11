package apps.amaralus.orch.core.init;

import apps.amaralus.orch.core.init.model.ProcessRequest;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static apps.amaralus.orch.core.common.BasePath.INIT_ROUTE;

@Component
public class InitRoute extends RouteBuilder {

    @Override
    public void configure() {
        from(INIT_ROUTE)
                .unmarshal()
                .json(ProcessRequest.class)
                .log(LoggingLevel.INFO, "${body}");
    }
}
