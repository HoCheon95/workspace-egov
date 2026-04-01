package kr.or.ddit.servlet04;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.arguments.CookieValueResolver;

@WebServlet("/04/state-read")
public class StateReadServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. request
        String reqNickName = (String) req.getAttribute("nickName");
        req.setAttribute("reqNickName", reqNickName);
        // 2. session
        String sessionNickName = (String) req.getSession().getAttribute("nickName");
        req.setAttribute("sessionNickName", sessionNickName);
        // 3. cookie
        req.setAttribute("cookieNickName", new CookieValueResolver().resolveCookieValue("nickName", req));
        
        
        String logicalViewName = "04/state-result";
        new ViewResolverComposite().resolveViewName(logicalViewName, req, resp);
    }
}
