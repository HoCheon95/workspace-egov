package kr.or.ddit.mvc;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.simple.AbstractControllerHandlerAdapter;
import kr.or.ddit.mvc.simple.AbstractControllerHandlerMapping;
import lombok.extern.slf4j.Slf4j;

/**
 * 정적 자원 요청을 포함한 모든 요청에 대한 Front Controller
 */
@Slf4j
public class DispatcherServlet extends HttpServlet{
    private ViewResolver viewResolver = new ViewResolverComposite();
    private HandlerMapping handlerMapping = new AbstractControllerHandlerMapping("kr.or.ddit");
    private HandlerAdapter handlerAdpter = new AbstractControllerHandlerAdapter();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.info("Front controller 인스턴스 생성");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI().substring(req.getContextPath().length()).split(";")[0];
        
        // 1. 정적 자원에 대한 요청 처리
        if(requestURI.startsWith("/resources")){
            log.info("{} 정적 자원 요청", requestURI);
            getServletContext().getNamedDispatcher("default").forward(req, resp);
            return;
        }

        Object controller = handlerMapping.findCommandHandler(req);

        if (controller == null) {
            resp.sendError(404, "%s 는 처리할 수 없는 요청".formatted(requestURI));
            return;
        }

        String lvn = handlerAdpter.invokeHandler(controller, req, resp);

        viewResolver.resolveViewName(lvn, req, resp);
    }
}