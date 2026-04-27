package kr.or.ddit.mvc.messageconverter;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface HttpMessageConverter {
    <T> T read(HttpServletRequest req, Class<T> objType) throws IOException;
    <T> void write(T obj, HttpServletResponse resp) throws IOException;
}
