package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.buyer.service.BuyerService;

@Controller
@Slf4j
public class BuyerReadController{
    private BuyerService service = new BuyerServiceImpl();

    @RequestMapping("/buyer/list")
    public ModelAndView buyerList(HttpServletRequest req, HttpServletResponse resp) {
        List<BuyerDto> buyerList = service.readBuyerList();
        ModelAndView mav = new ModelAndView();
        log.info("{}", buyerList.getFirst()
        );
        mav.addAttribute("buyerList", buyerList);
        mav.setViewName("buyer/buyerList");
        return mav;
    }

    @RequestMapping("/buyer/detail")
    public ModelAndView buyerDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String buyerId = req.getParameter("what");
        if(StringUtils.isBlank(buyerId)) {
            resp.sendError(400, "필수 파라미터 누락");
            return null;
        }

        ModelAndView mav = new ModelAndView();
        BuyerDto buyer =service.readBuyer(buyerId);
        mav.addAttribute("buyer", buyer);
        mav.setViewName("buyer/buyerDetail");
        return mav;
    }
}
