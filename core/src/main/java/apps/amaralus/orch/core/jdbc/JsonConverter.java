package apps.amaralus.orch.core.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

public abstract class JsonConverter<T> {

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    private final ObjectMapper objectMapper = new ObjectMapper();

    protected PGobject write(T source) {
        try {
            var json = objectMapper.writeValueAsString(source);
            var pgobject = new PGobject();
            pgobject.setType("json");
            pgobject.setValue(json);
            return pgobject;
        } catch (JsonProcessingException | SQLException e) {
            throw new JsonConversionException(e);
        }
    }

    protected T read(PGobject source) {
        try {
            return objectMapper.readValue(source.getValue(), entityClass);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException(e);
        }
    }

    @WritingConverter
    public abstract static class Writing<T> extends JsonConverter<T> implements Converter<T, PGobject> {
        @Override
        public PGobject convert(T source) {
            return write(source);
        }
    }

    @ReadingConverter
    public abstract static class Reading<T> extends JsonConverter<T> implements Converter<PGobject, T> {
        @Override
        public T convert(PGobject source) {
            return read(source);
        }
    }

    public static class JsonConversionException extends RuntimeException {
        public JsonConversionException(Throwable cause) {
            super(cause);
        }
    }
}
