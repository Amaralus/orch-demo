package apps.amaralus.orch.core.route;

import lombok.Data;

import java.util.UUID;

import static apps.amaralus.orch.core.route.RouteStatus.CREATED;

@Data
public class Route {
    private String routeTemplateId;
    private long clientId;
    private UUID routeId;
    private RouteStatus status = CREATED;
}
