package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;

@WebServlet("/member/leave-out")
public class MemberDeleteServlet extends HttpServlet{
    private ViewResolver viewResolver = new ViewResolverComposite();
    private MemberService service = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String memId = req.getUserPrincipal().getName();
        String memPass = req.getParameter("memPass");

        // 1. 인증
        
        // 2. 탈퇴 처리
        // 3. 로그아웃
    }
}
