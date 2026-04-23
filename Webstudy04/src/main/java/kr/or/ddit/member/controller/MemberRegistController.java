package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;

// @WebServlet("/member/regist")
@Controller
public class MemberRegistController {
    private MemberService service = new MemberServiceImpl();

    @RequestMapping("/member/regist")
    public String memberRegist(){
        return "member/registForm";
    }

    @RequestMapping(value = "/member/regist", method = RequestMethod.POST)
    public ModelAndView memberCreate(HttpServletRequest req){
        ModelAndView mav = new ModelAndView();

        // req.setCharacterEncoding("UTF-8");

        // 1. 17개 파라미터 수신 -> command Object 로 바인드

        Map<String, String[]> parameterMap = req.getParameterMap();
        MemberDto commandObject = PopulateUtils.populate(parameterMap, MemberDto.class);
        mav.addAttribute("member", commandObject);

        // 2. command object 검증
        Map<String, List<String>> errors =  ValidateUtils.validate(commandObject, InsertGroup.class);
        mav.addAttribute("errors", errors);

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
        mav.setViewName(lvn);
        return mav;
    }
}
