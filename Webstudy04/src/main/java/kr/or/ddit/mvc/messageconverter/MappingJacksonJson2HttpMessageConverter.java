package kr.or.ddit.mvc.messageconverter;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.di.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class MappingJacksonJson2HttpMessageConverter implements HttpMessageConverter{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T read(HttpServletRequest req, Class<T> objType) throws IOException {
        try (
            Reader reader = req.getReader();
        ) {
            return objectMapper.readValue(reader, objType);
        }
    }

    @Override
    public <T> void write(T obj, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try (
            Writer out = resp.getWriter();
        ) {
            objectMapper.writeValue(out, obj);
        }
    }

}
