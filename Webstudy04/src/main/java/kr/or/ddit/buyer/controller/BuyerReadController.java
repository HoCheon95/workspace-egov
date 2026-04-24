package kr.or.ddit.buyer.controller;

import java.util.List;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;
import kr.or.ddit.mvc.simple.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.buyer.service.BuyerService;

@Controller
@Slf4j
public class BuyerReadController{
    private BuyerService service = new BuyerServiceImpl();

    @RequestMapping("/buyer/list")
    public String buyerList(Model model) {
        List<BuyerDto> buyerList = service.readBuyerList();
        model.addAttribute("buyerList", buyerList);
        return "buyer/buyerList";
    }

    @RequestMapping("/buyer/detail")
    public ModelAndView buyerDetail(@RequestParam("what") String buyerId) {
        ModelAndView mav = new ModelAndView();
        BuyerDto buyer =service.readBuyer(buyerId);
        mav.addAttribute("buyer", buyer);
        mav.setViewName("buyer/buyerDetail");
        return mav;
    }
}
