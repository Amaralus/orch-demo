package apps.amaralus.orch.core.init.model;

import apps.amaralus.orch.core.RouteStatus;

import java.util.UUID;

public record ProcessResponse(UUID routeId, RouteStatus status) {
}
