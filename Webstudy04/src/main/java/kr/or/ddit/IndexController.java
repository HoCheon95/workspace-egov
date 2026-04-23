package kr.or.ddit;

import java.time.LocalDate;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;

@Controller
public class IndexController{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRequest() {
        String lvn = "index";
        LocalDate today = LocalDate.now();
        ModelAndView mav = new ModelAndView();
        mav.addAttribute("today", today);
        mav.setViewName(lvn);
        return mav;
    }
}
