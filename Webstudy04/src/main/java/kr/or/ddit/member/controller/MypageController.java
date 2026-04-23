package kr.or.ddit.member.controller;

import kr.or.ddit.auth.Authentication;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;

@Controller
public class MypageController{
    private MemberService service = new MemberServiceImpl();

    @RequestMapping("/member/mypage")
    public ModelAndView memberMypage(Authentication principal) {
        MemberDto member = service.readMember(principal.getName());

        ModelAndView mav = new ModelAndView();
        mav.addAttribute("member", member);
        mav.setViewName("member/mypage");

        return mav;
    }
}
