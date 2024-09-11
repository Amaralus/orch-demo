package orchdemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("core")
                .get("test").to("direct:test");

        from("direct:test")
                .transform().constant("hello");
    }
}
