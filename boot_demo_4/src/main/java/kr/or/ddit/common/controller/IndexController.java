package kr.or.ddit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        log.info("Index 진입");
        model.addAttribute("attrName", "컨트롤러가 전달한 모델");
        return "index";
    }

}
