package kr.or.ddit.member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/member/modify")
public class MemberModifyServlet extends HttpServlet {
    private ViewResolver viewResolver = new ViewResolverComposite();
    private MemberService service = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 로그인한 사용자 정보 조회
        Authentication authentication = (Authentication) req.getUserPrincipal();
        String username = authentication.getName();
        
        // 회원 정보 조회 (Service에 메서드가 있는 경우)
        // MemberDto member = service.readMember(username);
        // req.setAttribute("member", member);
        
        String lvn = "member/modify";
        viewResolver.resolveViewName(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lvn = null;
        try {
            // 회원 정보 수정 로직 추가 예정
            lvn = "redirect:/";
        } catch (Exception e) {
            lvn = "redirect:/member/modify";
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }
}
