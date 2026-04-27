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
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BuyerInsertController {
    @Autowired
    private BuyerService service;

    @RequestMapping("/buyer/create")
    public String formUi(){
        return "buyer/buyerForm";
    }
    
    @RequestMapping(value = "/buyer/create", method = RequestMethod.POST)
    public String formDataProcess(
        @Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerDto buyer,
        Map<String, List<String>> errors,
        Model model
    ) {
        String lvn = null;
        if(errors.isEmpty()) {
            boolean result = service.createBuyer(buyer);
            if (result) {
                lvn = "redirect:/buyer/detail?what="+buyer.getBuyerId();
            } else {
                model.addAttribute("message", "알수 없는 오류로 등록 실패. 좀따 다시.");
                lvn = "buyer/buyerForm";
            }
        } else {
            lvn = "buyer/buyerForm";
        }
        return lvn;
    }
}
