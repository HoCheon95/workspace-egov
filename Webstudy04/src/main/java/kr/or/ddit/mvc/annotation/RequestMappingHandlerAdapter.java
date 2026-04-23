package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.HandlerAdapter;
import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletRequestMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletResponseMethodArgumentResolver;
import kr.or.ddit.mvc.simple.ModelAndView;

public class RequestMappingHandlerAdapter implements HandlerAdapter{
    private List<HandlerMethodArgumentResolver> argumentResolvers;
    
    public RequestMappingHandlerAdapter() {
        argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new ServletRequestMethodArgumentResolver());
        argumentResolvers.add(new ServletResponseMethodArgumentResolver());
        argumentResolvers.add(new RequestParamMethodArgumentResolver());
    }

    public void addHandlerMethodArgumentResolver(HandlerMethodArgumentResolver resolver) {
        argumentResolvers.add(resolver);
    }

    private HandlerMethodArgumentResolver findArgumentResolver(Parameter parameter) {
        return argumentResolvers.stream()
                .filter(ar->ar.supportParameter(parameter))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("핸들러 어댑터에서 %s 를 처리할 argument resolver를 찾지 못했음.".formatted(parameter.getType())));
    }

    @Override
    public <T> String invokeHandler(T controllerInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestMappingInfo requestMappingInfo = (RequestMappingInfo) controllerInfo;

        Object controller =  requestMappingInfo.getControllerInstance();

        Method handlerMethod = requestMappingInfo.getHandlerMethod();

        Parameter[] parameters = handlerMethod.getParameters();

        Class<?> returnType = handlerMethod.getReturnType();

        Object returnValue;
        try {
            if (parameters.length == 0) {
                returnValue = handlerMethod.invoke(controller);
            } else {
                Object[] args = new Object[parameters.length];
                for (int i=0; i<parameters.length; i++) {
                    Parameter parameter = parameters[i];

                    HandlerMethodArgumentResolver resolver = findArgumentResolver(parameter);
                    args[i] = resolver.resolveArgument(parameter, req, resp);
                    
                }
                returnValue = handlerMethod.invoke(controller, args);
            }
            
            if (returnValue != null) {
                if(returnValue instanceof ModelAndView){
                    ModelAndView mav = (ModelAndView) returnValue;
                    mav.getModel().forEach((attributeName, attributeValue)->{
                        req.setAttribute(attributeName, attributeValue);
                    });
                    
                    return mav.getViewName();
                } else if(returnValue instanceof String) {
                    return (String) returnValue;
                } else {
                    throw new RuntimeException("핸들러 메소드의 반환타입을 처리할 수 없음.");
                }
            } else {
                return null;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    
}
