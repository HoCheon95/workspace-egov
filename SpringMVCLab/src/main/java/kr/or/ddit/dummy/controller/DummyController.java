package kr.or.ddit.dummy.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DummyController {
    
    @GetMapping("/dummy")
    public String dummy(
        Model model,
        @RequestParam(required = false, value = "param1") String param1
    ) {
        model.addAttribute("now", LocalDateTime.now());
        log.info("요청 파라미터 : {}", param1);
        return "dummy";
    }
}
