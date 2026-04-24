package kr.or.ddit.member.controller;

import jakarta.servlet.http.HttpSession;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;

@Controller
public class MemberDeleteController {
    private MemberService service = new MemberServiceImpl();

    @RequestMapping(value = "/member/leave-out", method = RequestMethod.POST)
    public String memberLeaveOut(
        @RequestParam("password") String password ,
        Authentication authentication,
        HttpSession session
    ) {

        String username = authentication.getName();

        MemberDto authToken = MemberDto.builder()
                        .memId(username)
                        .memPass(password)
                        .build();

        String lvn = null;
        try{
            service.removeMember(authToken);
            lvn = "redirect:/logout";
        } catch (AuthenticationException e){
            session.setAttribute("message", e.getMessage());
            lvn = "redirect:/member/mypage";
        }
        return lvn;
    }
}