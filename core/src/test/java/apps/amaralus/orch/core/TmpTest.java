package apps.amaralus.orch.core;

import apps.amaralus.orch.core.route.RouteTemplate;
import apps.amaralus.orch.core.route.RouteTemplateRepository;
import apps.amaralus.orch.core.route.TasksConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class TmpTest {

    @Autowired
    JdbcAggregateTemplate jdbcAggregateTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    RouteTemplateRepository routeTemplateRepository;

    @Test
    void test() {
        routeTemplateRepository.deleteAll();

        var routeTemplate = new RouteTemplate();
        routeTemplate.setId("test-route");
        var taskConfig = new TasksConfig();
        taskConfig.setName("task1");
        taskConfig.setNextTask("task2");
        routeTemplate.setTasksConfig(taskConfig);

        jdbcAggregateTemplate.insert(routeTemplate);

        var res = jdbcAggregateTemplate.findById("test-route", RouteTemplate.class);

        log.info("res: {}", res);

        assertTrue(true);
    }
}