package kr.or.ddit.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.common.exception.PkDuplicatedException;
import kr.or.ddit.dto.MemberDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;


@Slf4j
@Controller
@RequestMapping("/member/regist")
public class MemberRegistController {
    @Autowired
    private MemberService service;
    public static final String MODELNAME = "member";

    @ModelAttribute(MODELNAME)
    public MemberDTO member() {
        return new MemberDTO();
    }

    @GetMapping
    public String formUi(Model model){
        log.info("{}", model.getAttribute(MODELNAME));
        return "member/registForm";
    }

    @PostMapping
    public String formDataProcess(
        @Validated(InsertGroup.class) @ModelAttribute(MODELNAME) MemberDTO commandObject,
        BindingResult errors,
        RedirectAttributes redirectAttributes
    ){
        String errorName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
        String lvn = null;
        if (!errors.hasErrors()) {
            try {
                service.registerMember(commandObject);
                return "redirect:/";
            } catch (PkDuplicatedException e) {
                redirectAttributes.addFlashAttribute(MODELNAME, commandObject);
                redirectAttributes.addFlashAttribute(errorName, errors);
                errors.rejectValue("memId", "pkduplicated", "이미 사용 중인 아이디입니다.");
                lvn = "redirect:/member/regist";
            }
        } else {
            redirectAttributes.addFlashAttribute(MODELNAME, commandObject);
            redirectAttributes.addFlashAttribute(errorName, errors);
            lvn = "redirect:/member/regist";
        }
        return lvn;
    }
}
