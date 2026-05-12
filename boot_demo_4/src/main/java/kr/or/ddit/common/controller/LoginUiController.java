package kr.or.ddit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginUiController{

    @GetMapping("/login")
    public String login() {
        return "login/loginForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login/logoutForm";
    }
}