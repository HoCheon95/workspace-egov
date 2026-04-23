package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;

/**
 * 핸들러 메소드의 파라미터에 대응하는 인자를 생성하되,
 * 요청 파라미터로부터 그 인자를 꺼내기 위한 전략 객체.
 * String, int, long, double.. primitive type
 * Integer, Long ... wrapper type
 */
public class RequestParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public Boolean supportParameter(Parameter parameter) {
        RequestParam annotation = parameter.getAnnotation(RequestParam.class);
        Class<?> paramterType = parameter.getType();
        return (annotation != null) && (
            String.class.isAssignableFrom(paramterType)
            || ClassUtils.isPrimitiveOrWrapper(paramterType)

        );
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestParam annotation = parameter.getAnnotation(RequestParam.class);
        Class<?> paramterType = parameter.getType();
        String paramName = annotation.value();
        String paramValue = req.getParameter(paramName);
        if(annotation.required() && StringUtils.isBlank(paramValue)) {
            // BadRequestException 대응
            throw new IllegalArgumentException("%s 에 해당하는 요청 파라미터 없음.".formatted(paramName));
        }
        if(StringUtils.isBlank(paramValue)) {
            // 주의 구간
            paramValue = annotation.defaultValue();
        } 
        return resolveArgumentForType(paramterType, paramValue);
    }

    private <T> T resolveArgumentForType(Class<T> parameterType, String paramValue) {
        Object arg = null;
        if (parameterType.equals(Integer.class) || parameterType.equals(int.class)) {
            arg = Integer.parseInt(paramValue);
        } else if (parameterType.equals(Double.class) || parameterType.equals(double.class)) {
            arg = Double.parseDouble(paramValue);
        } else if (parameterType.equals(Boolean.class) || parameterType.equals(boolean.class)) {
            arg = Boolean.parseBoolean(paramValue);
        } else {
            arg = paramValue;
        }

        return (T) arg;
    }

}
