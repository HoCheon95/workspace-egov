package kr.or.ddit.mvc.simple;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AbstractController {

    String mappingCondition();

    ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
