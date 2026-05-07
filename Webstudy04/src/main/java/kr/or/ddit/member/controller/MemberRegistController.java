package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import kr.or.ddit.auth.Authentication;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.validate.groups.InsertGroup;

@Controller
public class MemberRegistController {               
    @Autowired
    private MemberService service;

    @RequestMapping("/member/regist")
    public String formUi(){
        return "member/registForm";
    }           

    @RequestMapping(value = "/member/regist", method = RequestMethod.POST)
    public String formDataProcess(
        @Validated(InsertGroup.class) @ModelAttribute("member") MemberDto commandObject,
        Map<String, List<String>> errors,
        Model model,
        Authentication authentication
    ){
        String lvn = null;
        // 3. 검증 통과
        if (errors.isEmpty()) {
            service.registMember(commandObject);
            // 3-1. 웰컴 페이지로 이동: redirect
            lvn = "redirect:/";
        } else {
            // 4. 검증에 통과하지 못함
            // registForm 이동 : forward
            lvn = "member/registForm";
        }
        return lvn;
    }
}
