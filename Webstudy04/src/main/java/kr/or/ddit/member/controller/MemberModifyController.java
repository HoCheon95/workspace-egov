package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;

@Controller
public class MemberModifyController {
    private MemberService service = new MemberServiceImpl();

    @RequestMapping("/member/modify")
    public ModelAndView memberModify(Authentication authentication) {
        // 현재 로그인한 사용자 정보 조회
        String username = authentication.getName();

        ModelAndView mav = new ModelAndView();
        // 회원 정보 조회 (Service에 메서드가 있는 경우)
        MemberDto member = service.readMember(username);
        mav.addAttribute("member", member);

        // modifyForm.jsp 화면 전화
        mav.setViewName("member/modifyForm");
        return mav;
    }

    @RequestMapping(value = "/member/modify", method = RequestMethod.POST)
    public ModelAndView memberModifyFromProcess(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();

        // 1. 16개 파라미터 수신 -> command Object 로 바인드
        Map<String, String[]> parameterMap = req.getParameterMap();
        MemberDto commandObject = PopulateUtils.populate(parameterMap, MemberDto.class);
        // 현재 로그인한 사용자의 ID를 커맨드 객체에 세팅한다.
        commandObject.setMemId(req.getUserPrincipal().getName());
        mav.addAttribute("member", commandObject);

        // 2. command object 검증
        Map<String, List<String>> errors = ValidateUtils.validate(commandObject, UpdateGroup.class);
        mav.addAttribute("errors", errors);

        String lvn = null;
        // 3. 검증 통과
        if (errors.isEmpty()) {
            try {
                service.modifyMember(commandObject);
                // 3-1. 마이 페이지로 이동: redirect
                lvn = "redirect:/member/mypage";
            } catch (Exception e) {
                // AuthenticationException이 별도로 정의되어 있지 않다면 일반 Exception으로 처리하거나
                // 해당 예외 클래스를 import 해야 한다.
                req.setAttribute("message", "비밀번호 오류");
                lvn = "member/modifyForm";
            }
        } else {
            // 4. 검증에 통과하지 못함
            // modifyForm 이동 : forward
            lvn = "member/modifyForm";
        }
        mav.setViewName(lvn);
        return mav;
    }
}