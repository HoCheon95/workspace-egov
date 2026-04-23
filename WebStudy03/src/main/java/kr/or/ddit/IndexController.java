package kr.or.ddit;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.mvc.simple.ModelAndView;

public class IndexController implements AbstractController{

    @Override
    public String mappingCondition() {
        return "/";
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String lvn = "index";
        LocalDate today = LocalDate.now();
        ModelAndView mav = new ModelAndView();
        mav.addAttribute("today", today);
        mav.setViewName(lvn);
        return mav;
    }

}
