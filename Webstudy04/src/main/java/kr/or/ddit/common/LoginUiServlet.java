package kr.or.ddit.common;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/login")
public class LoginUiServlet extends HttpServlet{
    private ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lvn = "login/loginForm";
        viewResolver.resolveViewName(lvn, req, resp);
    }
    
}