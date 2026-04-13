package kr.or.ddit.member.controller;

import java.io.IOException;

import org.jsoup.internal.StringUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/member/change-password")
public class ChangePasswordServlet extends HttpServlet{
    private ViewResolver viewResolver = new ViewResolverComposite();
    private MemberService service = new MemberServiceImpl();
    HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lvn = "member/change-password";
        viewResolver.resolveViewName(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 검증
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String retypePassword = req.getParameter("retypePassword");

        // 필수 파라미터 검증 : 누락시 400 전송
        if (StringUtil.isBlank(oldPassword) || StringUtil.isBlank(newPassword) || StringUtil.isBlank(retypePassword)) {
            resp.sendError(400, "필수 파라미터 누락");
            return;
        }
        String lvn = null;
        HttpSession session = req.getSession();
        // pw1 == pw2 : 통과하지 못하면? 적절한 메시지와 함께 change-password 로 redirect 이동 
        if(newPassword.equals(retypePassword)){
            // 2. 비번 수정 로직
            // Principal 로부터 username 을 확보
            Authentication authentication = (Authentication) req.getUserPrincipal();
            String username = authentication.getName();
            try{
                service.changePassword(username, oldPassword, newPassword);
                // 3. 성공 : 로그아웃
                lvn = "redirect:/logout";
            } catch (AuthenticationException e){
                // 4. 실패 (AuthnticationException 처리) : 적절한 메시지와 함께 change-password 로 redirect 이동
                lvn = "redirect:/member/change-password";    
                session.setAttribute("message", e.getMessage());
            }

        } else {
            lvn = "redirect:/member/change-password";
            session.setAttribute("message", "비밀번호 재입력 확인");
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }
}
