package kr.or.ddit.member.controller;

import jakarta.servlet.http.HttpSession;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;

@Controller
public class ChangePasswordController {
    private MemberService service = new MemberServiceImpl();
    HttpSession session;

    @RequestMapping("/member/change-password")
    public String formUi() {
        return "member/change-password";
    }

    @RequestMapping(value = "/member/change-password", method = RequestMethod.POST)
    public String formDataProcess(
        @RequestParam(value = "oldPassword", required = true) String oldPassword, 
        @RequestParam(value = "newPassword", required = true) String newPassword, 
        @RequestParam(value = "retypePassword", required = true) String retypePassword,
        @RequestParam(value = "dummy", required = false, defaultValue = "1") int dummy,
        HttpSession session,
        Authentication authentication
    ) {

        // // 필수 파라미터 검증 : 누락시 400 전송
        // if (StringUtil.isBlank(oldPassword) || StringUtil.isBlank(newPassword) || StringUtil.isBlank(retypePassword)) {
        //     try {
        //         resp.sendError(400, "필수 파라미터 누락");
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        //     return null;
        // }
        String lvn = null;
        // pw1 == pw2 : 통과하지 못하면? 적절한 메시지와 함께 change-password 로 redirect 이동 
        if(newPassword.equals(retypePassword)){
            // 2. 비번 수정 로직
            // Principal 로부터 username 을 확보
            String username = authentication.getName();
            try{
                service.changePassword(username, oldPassword, newPassword);
                // 3. 성공 : 로그아웃
                lvn = "redirect:/logout";
            } catch (AuthenticationException e){
                // 4. 실패 (AuthnticationException 처리) : 적절한 메시지와 함께 change-password 로 redirect 이동
                lvn = "redirect:/member/change-password";    
                session.setAttribute("message", e.getMessage());
            }

        } else {
            lvn = "redirect:/member/change-password";
            session.setAttribute("message", "비밀번호 재입력 확인");
        }
        return lvn;
    }
}
