package kr.or.ddit.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;

@Controller
public class MypageController{
    @Autowired
    private MemberService service;

    @RequestMapping("/member/mypage")
    public ModelAndView memberMypage(Authentication principal) {
        MemberDTO member = service.readMember(principal.getName());

        ModelAndView mav = new ModelAndView();
        mav.addObject("member", member);
        mav.setViewName("member/mypage");

        return mav;
    }
}
