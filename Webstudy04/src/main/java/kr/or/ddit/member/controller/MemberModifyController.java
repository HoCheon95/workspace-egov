package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import kr.or.ddit.auth.Authentication;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberModifyController {
    private MemberService service = new MemberServiceImpl();

    @RequestMapping("/member/modify")
    public String memberModify(Authentication authentication, Model model) {
        // 현재 로그인한 사용자 정보 조회
        String username = authentication.getName();

        // 회원 정보 조회 (Service에 메서드가 있는 경우)
        MemberDto member = service.readMember(username);
        model.addAttribute("member", member);

        return "member/modifyForm";
    }

    @RequestMapping(value = "/member/modify", method = RequestMethod.POST)
    public String memberModifyFromProcess(
        @Validated(UpdateGroup.class) @ModelAttribute("member") MemberDto commandObject,
        Map<String, List<String>> errors,
        Model model,
        Authentication authentication
    ) {
        log.info("model 내의 속성 체크 : {}", model.containsAttribute("member"));
        commandObject.setMemId(authentication.getName());

        String lvn = null;
        // 3. 검증 통과
        if (errors.isEmpty()) {
            try {
                service.modifyMember(commandObject);
                // 3-1. 마이 페이지로 이동: redirect
                lvn = "redirect:/member/mypage";
            } catch (Exception e) {
                model.addAttribute("message", "비밀번호 오류");
                lvn = "member/modifyForm";
            }
        } else {
            // 4. 검증에 통과하지 못함
            // modifyForm 이동 : forward
            lvn = "member/modifyForm";
        }
        return lvn;
    }
}