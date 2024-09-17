package apps.amaralus.orch.core.route;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class RouteContextProvider {

    private final Map<UUID, Route> activeRoutes = new ConcurrentHashMap<>();

    public Optional<Route> findActiveRoute(String routeTemplateId, long clientId) {
        log.debug("Поиск локального активного маршрута, routeTemplateId={}, clientId={}", routeTemplateId, clientId);
        return activeRoutes.values().stream()
                .filter(activeRoute ->
                        activeRoute.getRouteTemplateId().equals(routeTemplateId)
                        && activeRoute.getClientId() == clientId)
                .findFirst();

        // todo поиск активного маршрута в бд
    }

    public Route initRoute(String routeTemplateId, long clientId) {
        var route = new Route();
        route.setRouteId(UUID.randomUUID());
        route.setRouteTemplateId(routeTemplateId);
        route.setClientId(clientId);

        activeRoutes.put(route.getRouteId(), route);

        log.debug("Создан инстанс маршрута id ={}, routeTemplateId={}, clientId={}",
                route.getRouteId(), routeTemplateId, clientId);
        return route;

        // todo сделать инсерт в бд
    }

    public void removeRoute(UUID routeId) {
        activeRoutes.remove(routeId);
    }
}

