package apps.amaralus.orch.core.init;

import apps.amaralus.orch.core.Route;
import apps.amaralus.orch.core.RouteContextProvider;
import apps.amaralus.orch.core.init.model.ProcessRequest;
import apps.amaralus.orch.core.init.model.ProcessResponse;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static apps.amaralus.orch.core.RouteStatus.CREATED;
import static apps.amaralus.orch.core.RouteStatus.SUSPENDED;
import static apps.amaralus.orch.core.common.BasePath.HANDLE_ROUTE;
import static apps.amaralus.orch.core.common.BasePath.INIT_ROUTE;

@Component
@RequiredArgsConstructor
public class InitRoute extends RouteBuilder {

    private final RouteContextProvider routeContextProvider;

    @Override
    public void configure() {
        from(INIT_ROUTE)
                .unmarshal()
                .json(ProcessRequest.class)
                .bean(InitRoute.class, "initialize")
                .choice()
                    .when(body().method("getStatus").in(CREATED, SUSPENDED))
                    .to(HANDLE_ROUTE)
                .otherwise()
                    .transform()
                    .body(this::routeToResponse)
                    .marshal().json();
    }
//    предотвращаем фантомную запись
// todo   @Transactional(isolation = SERIALIZABLE)
    public Route initialize(ProcessRequest processRequest) {
        return routeContextProvider
                .findActiveRoute(processRequest.getRouteTemplateId(), processRequest.getClientId())
                .orElseGet(() -> routeContextProvider.initRoute(processRequest.getRouteTemplateId(), processRequest.getClientId()));
    }

    private Object routeToResponse(Object object) {
        var route = (Route) object;
        return new ProcessResponse(route.getRouteId(), route.getStatus());
    }
}
