package kr.or.ddit.buyer.controller;

import java.util.List;

import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.ResponseBody;
import kr.or.ddit.mvc.simple.ModelAndView;
import kr.or.ddit.buyer.service.BuyerService;

@Controller
public class BuyerReadController{
    @Autowired
    private BuyerService service;

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

    @RequestMapping("/buyer/detail/json")
    @ResponseBody
    public BuyerDto buyerDetailJson(@RequestParam("what") String buyerId) {
        return service.readBuyer(buyerId);
    }
}
