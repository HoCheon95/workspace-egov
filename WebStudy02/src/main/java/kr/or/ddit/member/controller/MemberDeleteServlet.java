package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.DeleteGroup;

@WebServlet("/member/leave-out")
public class MemberDeleteServlet extends HttpServlet{
    private MemberService service = new MemberServiceImpl();
    private ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getUserPrincipal().getName();
        String password = req.getParameter("password");

        if(StringUtils.isBlank(password)) {
            resp.sendError(400, "비밀번호 누락(필수 파라미터 검증 오류");
            return;
        }
        
        MemberDto authToken = MemberDto.builder()
                        .memId(username)
                        .memPass(password)
                        .build();

        String lvn = null;
        try{
            service.removeMember(authToken);
            lvn = "redirect:/logout";
        } catch (AuthenticationException e){
            req.getSession().setAttribute("message", e.getMessage());
            lvn = "redirect:/member/mypage";
        }

        viewResolver.resolveViewName(lvn, req, resp);
    }
}
