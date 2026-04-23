package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class MemberListController {
    private MemberService service = new MemberServiceImpl();

    @RequestMapping("/admin/member-list")
    public String doGet(HttpServletRequest req, Writer writer, HttpServletResponse resp) {
        String accept = req.getHeader("accept");

        List<MemberDto> memberList = service.readMemberList();
        if (accept.contains("json")) {
            resp.setContentType("application/json");
            Gson gson = new Gson();
            gson.toJson(memberList, writer);
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            req.setAttribute("memberList", memberList);
            return "member/memberList";
        }
    }
}