package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Parameter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * {@link HttpServletResponse}
 * {@link OutputStream}
 * {@link Writer}
 */
public class ServletResponseMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public Boolean supportParameter(Parameter parameter) {
        Class<?> parameterType = parameter.getType();
        return ServletResponse.class.isAssignableFrom(parameterType)
                || OutputStream.class.isAssignableFrom(parameterType)
                || Writer.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Class<?> parameterType = parameter.getType();
        Object arg = null;
        if (ServletResponse.class.isAssignableFrom(parameterType)) {
            arg = resp;
        } else if (OutputStream.class.isAssignableFrom(parameterType)) {
            arg = resp.getOutputStream();
        } else if (Writer.class.isAssignableFrom(parameterType)) {
            arg = resp.getWriter();
        }else {
            throw new RuntimeException("%s 는 현재 resolver 로 해결할 수 없음.".formatted(parameter));
        }
        return arg;
    }
}
