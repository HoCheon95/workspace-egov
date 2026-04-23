package kr.or.ddit.mvc.simple;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.HandlerAdapter;

public class AbstractControllerHandlerAdapter implements HandlerAdapter{

    @Override
    public <T> String invokeHandler(T controllerInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AbstractController controller = (AbstractController) controllerInfo;

        ModelAndView mav = controller.handleRequest(req, resp);

        mav.getModel().forEach((attributeName, attributeValue)->{
            req.setAttribute(attributeName, attributeValue);
        });
        return mav.getViewName();
    }
}
