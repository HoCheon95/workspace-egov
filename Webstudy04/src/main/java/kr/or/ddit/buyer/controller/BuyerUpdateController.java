package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Map;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.validate.groups.UpdateGroup;

@Controller
public class BuyerUpdateController {
    @Autowired
    private BuyerService service;

    @RequestMapping("/buyer/modify")
    public String formUi(
        @RequestParam(value = "what") String buyerId,
        Model model
    ) {
        BuyerDto buyer = service.readBuyer(buyerId);
        model.addAttribute("buyer", buyer);
        return "buyer/buyerForm";
    }

    @RequestMapping(value = "/buyer/modify", method = RequestMethod.POST)
    public String formDataProcess(
        @Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerDto buyer,
        Map<String, List<String>> errors,
        Model model
    ) {
        String lvn = null;
        if(errors.isEmpty()) {
            boolean result = service.modifyBuyer(buyer);
            if (result) {
                lvn = "redirect:/buyer/detail?what="+buyer.getBuyerId();
            } else {
                model.addAttribute("message", "수정 실패");
                lvn = "buyer/buyerForm";
            }
        } else {
            lvn = "buyer/buyerForm";
        }
        return lvn;
    }

}
