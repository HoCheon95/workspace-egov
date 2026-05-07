package kr.or.ddit.buyer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.dto.BuyerDto;


@Controller
@RequestMapping("/buyer")
public class BuyerReadController {
    @Autowired
    private BuyerService buyerService;

    @GetMapping("/list/aggrid")
    public String buyerListAgGrid() {
        return "buyer/aggrid";
    }

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<BuyerDto> buyerListJson(Model model) {
        List<BuyerDto> bList = buyerService.readBuyerList();
        return bList;
    }

    @GetMapping(value = "/list", produces = "text/html")
    public String buyerList(Model model) {
        List<BuyerDto> bList = buyerService.readBuyerList();
        model.addAttribute("buyerList", bList);
        return "buyer/buyerList";
    }

    @GetMapping("/detail")
    public String buyerDetail(@RequestParam("id") String buyerId, Model model) {
        BuyerDto buyerDto = buyerService.readBuyer(buyerId);
        model.addAttribute("buyer", buyerDto);
        return "buyer/buyerDetail";
    }

    @GetMapping("/list/json")
    @ResponseBody
    public List<BuyerDto> buyerListJson(@RequestParam("id") String lprodGu) {
        List<BuyerDto> buyerList = buyerService.readBuyerListByLprodGu(lprodGu);
        return buyerList;
    }

}
