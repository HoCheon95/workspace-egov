package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Parameter;
import java.security.Principal;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * {@link HttpServletRequest}
 * {@link HttpSession}
 * {@link Principal}
 * {@link InputStream}
 * {@link Reader}
 */
public class ServletRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public Boolean supportParameter(Parameter parameter) {
        Class<?> parameterType = parameter.getType();
        return ServletRequest.class.isAssignableFrom(parameterType)
                || HttpSession.class.isAssignableFrom(parameterType)
                || Principal.class.isAssignableFrom(parameterType)
                || InputStream.class.isAssignableFrom(parameterType)
                || Reader.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Class<?> parameterType = parameter.getType();
        Object arg = null;
        if (ServletRequest.class.isAssignableFrom(parameterType)) {
            arg = req;
        } else if (HttpSession.class.isAssignableFrom(parameterType)) {
            arg = req.getSession();
        } else if (Principal.class.isAssignableFrom(parameterType)) {
            arg = req.getUserPrincipal();
        } else if (InputStream.class.isAssignableFrom(parameterType)) {
            arg = req.getInputStream();
        } else if (Reader.class.isAssignableFrom(parameterType)) {
            arg = req.getReader();
        } else {
            throw new RuntimeException("%s 는 현재 resolver 로 해결할 수 없음.".formatted(parameter));
        }
        return arg;
    }

}
