package apps.amaralus.orch.core.init.model;

import lombok.Data;

@Data
public class ProcessRequest {
    private String routeTemplateId;
    private long clientId;
}
