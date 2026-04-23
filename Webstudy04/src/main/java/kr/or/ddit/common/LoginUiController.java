package kr.or.ddit.common;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class LoginUiController{

    @RequestMapping("/login")
    public String login() {
        return "login/loginForm";
    }
}