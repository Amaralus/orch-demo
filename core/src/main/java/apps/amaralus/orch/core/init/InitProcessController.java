package apps.amaralus.orch.core.init;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static apps.amaralus.orch.core.common.BasePath.*;

@Component
public class InitProcessController extends RouteBuilder {

    @Override
    public void configure() {
        rest(BASE_URL)
                .post(PROCESS_URL)
                .consumes("application/json")
                .to(INIT_ROUTE);
    }
}
