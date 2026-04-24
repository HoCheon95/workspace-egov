package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;

public class ErrorsMapMethodArgumentResolver implements HandlerMethodArgumentResolver{

    @Override
    public Boolean supportParameter(Parameter parameter) {
        Class<?> parameterType =  parameter.getType();
        return Map.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Model model = (Model) req.getAttribute(RequestMappingHandlerAdapter.MODELATTRIBUTENAME);
        return model.getAttribute("errors");
    }

}
