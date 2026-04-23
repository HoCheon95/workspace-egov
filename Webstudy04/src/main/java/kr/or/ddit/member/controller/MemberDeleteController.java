package kr.or.ddit.member.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;

@Controller
public class MemberDeleteController {
    private MemberService service = new MemberServiceImpl();

    @RequestMapping(value = "/member/leave-out", method = RequestMethod.POST)
    public ModelAndView memberLeaveOut(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView();
        // req.setCharacterEncoding("UTF-8");

        String username = req.getUserPrincipal().getName();
        String password = req.getParameter("password");

        if(StringUtils.isBlank(password)) {
            try {
                resp.sendError(400, "비밀번호 누락(필수 파라미터 검증 오류");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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

        mav.setViewName(lvn);
        return mav;
    }
}
