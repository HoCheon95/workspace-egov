package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

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

@WebServlet("/admin/member-list")
public class MemberListServlet extends HttpServlet {
    private MemberService service = new MemberServiceImpl();
    private ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accept = req.getHeader("accept");

        List<MemberDto> memberList = service.readMemberList();
        if (accept.contains("json")) {
            resp.setContentType("application/json");
            Gson gson = new Gson();
            gson.toJson(memberList, resp.getWriter());
            
        } else {
            req.setAttribute("memberList", memberList);
            String lvn = "member/memberList";
            viewResolver.resolveViewName(lvn, req, resp);
        }

    }
}


