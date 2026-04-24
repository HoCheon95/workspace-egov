package kr.or.ddit;

import java.time.LocalDate;

import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class IndexController{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest(Model model) {
        String lvn = "index";
        LocalDate today = LocalDate.now();
        model.addAttribute("today", today);
        return lvn;
    }
}
