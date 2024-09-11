package apps.amaralus.orch.core;

import lombok.Data;

import java.util.UUID;

@Data
public class Route {
    private String routeTemplateId;
    private long clientId;
    private UUID routeId;
}
