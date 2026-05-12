package kr.or.ddit.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;

@Controller
public class MemberDeleteController {
    @Autowired
    private MemberService service;

    @Autowired
    private LogoutHandler logoutHandler;

    @RequestMapping(value = "/member/leave-out", method = RequestMethod.POST)
    public String memberLeaveOut(
        @RequestParam("password") String password ,
        Authentication authentication,
        HttpSession session,
        RedirectAttributes redirectAttributes,
        HttpServletRequest req,
        HttpServletResponse resp
    ) {

        String username = authentication.getName();

        MemberDTO authToken = MemberDTO.builder()
                        .memId(username)
                        .memPass(password)
                        .build();

        String lvn = null;
        try{
            service.removeMember(authToken);
            logoutHandler.logout(req, resp, authentication);
            lvn = "redirect:/";
        } catch (AuthenticationException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            lvn = "redirect:/member/mypage";
        }
        return lvn;
    }
}