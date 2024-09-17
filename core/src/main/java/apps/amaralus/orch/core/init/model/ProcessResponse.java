package apps.amaralus.orch.core.init.model;

import apps.amaralus.orch.core.route.RouteStatus;

import java.util.UUID;

public record ProcessResponse(UUID routeId, RouteStatus status) {
}
