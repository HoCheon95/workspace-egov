package kr.or.ddit.member.controller;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/change-password")
@RequiredArgsConstructor
public class ChangePasswordController {
    @Autowired
    private MemberService service;
    private final LogoutHandler logoutHandler;

    @GetMapping
    public String formUi() {
        return "member/change-password";
    }

    @PostMapping
    public String formDataProcess(
        @RequestParam(value = "oldPassword", required = true) String oldPassword, 
        @RequestParam(value = "newPassword", required = true) String newPassword, 
        @RequestParam(value = "retypePassword", required = true) String retypePassword,
        @RequestParam(value = "dummy", required = false, defaultValue = "1") int dummy,
        Authentication authentication,
        RedirectAttributes redirectAttributes,
        HttpServletRequest req,
        HttpServletResponse resp
    ) {

        String lvn = null;
        // pw1 == pw2 : 통과하지 못하면? 적절한 메시지와 함께 change-password 로 redirect 이동 
        if(newPassword.equals(retypePassword)){
            // 2. 비번 수정 로직
            // Principal 로부터 username 을 확보
            String username = authentication.getName();
            try{
                service.changePassword(username, oldPassword, newPassword);
                // 3. 성공 : 로그아웃
                logoutHandler.logout(req, resp, authentication);
                lvn = "redirect:/";
            } catch (AuthenticationException e){
                // 4. 실패 (AuthnticationException 처리) : 적절한 메시지와 함께 change-password 로 redirect 이동
                lvn = "redirect:/member/change-password";    
                redirectAttributes.addFlashAttribute("message", e.getMessage());
            }

        } else {
            lvn = "redirect:/member/change-password";
            redirectAttributes.addFlashAttribute("message", "비밀번호 재입력 확인");
        }
        return lvn;
    }
}
