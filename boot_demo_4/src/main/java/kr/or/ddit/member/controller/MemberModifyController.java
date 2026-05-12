package kr.or.ddit.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/modify")
public class MemberModifyController {
    @Autowired
    private MemberService service;

    @GetMapping
    public String memberModify(Authentication authentication, Model model) {
        // 현재 로그인한 사용자 정보 조회
        String username = authentication.getName();

        if (!model.containsAttribute("member")) {
            MemberDTO member = service.readMember(username);
            model.addAttribute("member", member);
        }

        return "member/modifyForm";
    }

    @PostMapping
    public String memberModifyFromProcess(
        @Validated(UpdateGroup.class) @ModelAttribute("member") MemberDTO commandObject,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        Authentication authentication
    ) {
        log.info("model 내의 속성 체크 : {}", model.containsAttribute("member"));
        commandObject.setMemId(authentication.getName());

        String lvn = null;
        // 3. 검증 통과
        if (!bindingResult.hasErrors()) {
            try {
                service.modifyMember(commandObject);
                // 3-1. 마이 페이지로 이동: redirect
                lvn = "redirect:/member/mypage";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("member", commandObject);
                redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
                lvn = "redirect:/member/modify";
            }
        } else {
            // 4. 검증에 통과하지 못함
            // modifyForm 이동 : forward
            redirectAttributes.addFlashAttribute("member", commandObject);
            String errorName = BindingResult.MODEL_KEY_PREFIX + "member";
            redirectAttributes.addFlashAttribute(errorName, bindingResult);
            lvn = "redirect:/member/modify";
        }
        return lvn;
    }
}