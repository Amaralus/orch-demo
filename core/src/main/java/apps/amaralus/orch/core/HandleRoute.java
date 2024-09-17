package apps.amaralus.orch.core;

import apps.amaralus.orch.core.init.model.ProcessResponse;
import apps.amaralus.orch.core.route.Route;
import apps.amaralus.orch.core.route.RouteContextProvider;
import apps.amaralus.orch.core.route.RouteStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static apps.amaralus.orch.core.common.BasePath.HANDLE_ROUTE;

@Component
@RequiredArgsConstructor
@Slf4j
public class HandleRoute extends RouteBuilder {

    private final RouteContextProvider routeContextProvider;

    @Override
    public void configure()  {
        from(HANDLE_ROUTE)
                .bean(HandleRoute.class, "handle");
    }

    public ProcessResponse handle(Route route) {
        log.debug("Обрабатываю маршрут routeTemplate={}, id={}", route.getRouteTemplateId(), route.getRouteId());
        route.setStatus(RouteStatus.FINISHED);
        routeContextProvider.removeRoute(route.getRouteId());
        return new ProcessResponse(route.getRouteId(), route.getStatus());
    }
}
