package kr.or.ddit.member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/member/mypage")
public class MypageServlet extends HttpServlet {
    private MemberService service = new MemberServiceImpl();
    private ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인된 사용자 ID 확인
        String memId = req.getUserPrincipal().getName();

        // 호출하여 회원 데이터 조회
        MemberDto member = service.readMember(memId);

        // JSP 전달 준비
        req.setAttribute("member", member);

        // mypage.jsp 화면 전화
        String lvn = "member/mypage";
        viewResolver.resolveViewName(lvn, req, resp);

    }
}
