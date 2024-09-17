package apps.amaralus.orch.core.route;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class RouteTemplate {
    @Id
    private String id;
    @Column
    private TasksConfig tasksConfig;

}
