package apps.amaralus.orch.core.route;

import apps.amaralus.orch.core.jdbc.JsonConverter;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class TasksConfig {
    private String name;
    private String nextTask;

    @Component
    public static class TasksConfigToJsonConverter extends JsonConverter.Writing<TasksConfig> {}

    @Component
    public static class JsonToTasksConfigConverter extends JsonConverter.Reading<TasksConfig> {}
}
