package kr.or.ddit.servlet04;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/04/state-create")
public class StateCreateServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logicalViewName = "04/state-form";
        new ViewResolverComposite().resolveViewName(logicalViewName, req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nickName = req.getParameter("nickName");
        if(StringUtils.isBlank(nickName)){
            resp.sendError(400,"필수 파라미터");
            return;
        }

        req.setAttribute("nickName", "요청에 저장된 %s".formatted(nickName));
        req.getSession().setAttribute("nickName", "세션에 저장된 %s".formatted(nickName));

        String cookieValue = URLEncoder.encode("쿠키에 저장된 %s".formatted(nickName), "UTF-8");
        Cookie nickNameCookie = new Cookie("nickName", cookieValue);
        nickNameCookie.setMaxAge(60 * 60 * 24 * 7);
        nickNameCookie.setPath(req.getContextPath());
        resp.addCookie(nickNameCookie);

        

        String logicalViewName = "redirect:/04/state-read";
        new ViewResolverComposite().resolveViewName(logicalViewName, req, resp);
    }
    
}
