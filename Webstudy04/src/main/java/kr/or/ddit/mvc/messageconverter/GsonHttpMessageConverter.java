package kr.or.ddit.mvc.messageconverter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GsonHttpMessageConverter implements HttpMessageConverter {
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public <T> T read(HttpServletRequest req, Class<T> objType) throws IOException {
        try (
            Reader reader = req.getReader();
        ) {
            return gson.fromJson(reader, objType);
        }
    }

    @Override
    public <T> void write(T obj, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try (
            Writer out = resp.getWriter();
        ) {
            gson.toJson(obj, out);
        }
    }

}
