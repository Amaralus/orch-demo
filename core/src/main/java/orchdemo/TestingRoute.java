package orchdemo;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("core")
                .get("test/{id}").to("seda:test");

        from("seda:test")
                .routingSlip().method(TestingRoute.DynamicRouter.class, "route");

        from("seda:bar")
                .transform().simple("bar ${header.id}");

        from("seda:baz")
                .transform().simple("baz ${header.id}");
    }

    @Component
    public static class DynamicRouter {

        public String route(Message message) {
            return "123".equals(message.getHeader("id"))
                    ? "seda:bar"
                    : "seda:baz";
        }
    }
}
