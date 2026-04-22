package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.HandlerAdapter;
import kr.or.ddit.mvc.simple.ModelAndView;

public class RequestMappingHandlerAdapter implements HandlerAdapter{

    @Override
    public <T> String invokeHandler(T controllerInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestMappingInfo requestMappingInfo = (RequestMappingInfo) controllerInfo;

        Object controller =  requestMappingInfo.getControllerInstance();

        Method handlerMethod = requestMappingInfo.getHandlerMethod();

        ModelAndView mav;
        try {
            
            mav = (ModelAndView) handlerMethod.invoke(controller, req, resp);
            if (mav != null) {
                mav.getModel().forEach((attributeName, attributeValue)->{
                    req.setAttribute(attributeName, attributeValue);
                });
                
                return mav.getViewName();
            } else {
                return null;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
