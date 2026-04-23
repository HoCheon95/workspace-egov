package kr.or.ddit.mvc;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InternalResourceViewResolver implements ViewResolver{

    @Override
    public void resolveViewName(String logicalViewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prifix = "/WEB-INF/views/";
        String suffix =".jsp";
        String view = prifix + logicalViewName + suffix;
        req.getRequestDispatcher(view).forward(req, resp);
    }
}
