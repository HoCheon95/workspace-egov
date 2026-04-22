package kr.or.ddit;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;

@Controller
public class IndexController{
    
    public void test() {}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String lvn = "index";
        LocalDate today = LocalDate.now();
        ModelAndView mav = new ModelAndView();
        mav.addAttribute("today", today);
        mav.setViewName(lvn);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView handleRequest2(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String lvn = "index";
        LocalDate today = LocalDate.now();
        ModelAndView mav = new ModelAndView();
        mav.addAttribute("today", today);
        mav.setViewName(lvn);
        return mav;
    }
}
