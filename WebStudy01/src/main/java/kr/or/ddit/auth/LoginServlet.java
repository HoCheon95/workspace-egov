package kr.or.ddit.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private ViewResolver viewResolver = new ViewResolverComposite();
    AuthenticateService service = new AuthenticateService();
    String logicalViewName = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logicalViewName = "login/loginForm";
        viewResolver.resolveViewName(logicalViewName, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. characterEncoding 설정
        req.setCharacterEncoding("UTF-8");

        // 2. 파라미터 수신
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 3. 검증 : 로그인 폼으로 이동 redirect, message 전달
        MemberDto member = service.authenticate(username, password);
        
        if(member == null){
            req.getSession().setAttribute("errorMessage", "인증 실패");
            logicalViewName = "redirect:/login";
        } else {
            // 4. 인증 여부 확인 (서비스 의존 관계)
            req.getSession().setAttribute("loginUser", member);
            logicalViewName = "redirect:/";
        }

        // 5. 인증 성공 : 웰컴페이지 이동, sessionScope : authMember, MemberDto/ 실패 : 로그인 폼으로 redirect, message 전달 session 스코프를 통해서 flash....
        viewResolver.resolveViewName(logicalViewName, req, resp);
    }
    
}
