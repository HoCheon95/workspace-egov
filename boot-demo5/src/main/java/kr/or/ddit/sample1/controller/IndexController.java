package kr.or.ddit.sample1.controller;

import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Locale clientLocale, Model model, Authentication authentication) {
        log.info("인증 객체 : {}", authentication);
        // SecurityContextHolder.getContext().getAuthentication();
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("clientLocale", clientLocale);

        return "index";
    }
}

