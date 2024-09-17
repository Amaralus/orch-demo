package apps.amaralus.orch.core.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JdbcConfiguration extends AbstractJdbcConfiguration {

    private final List<? extends JsonConverter<?>> converters;

    @Override
    protected List<?> userConverters() {
        log.info("converters: {}", converters);
        return converters;
    }
}
