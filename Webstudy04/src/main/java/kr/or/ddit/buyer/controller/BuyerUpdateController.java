package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Map;

import kr.or.ddit.auth.Authentication;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BuyerUpdateController {
    private BuyerService service = new BuyerServiceImpl();

    @RequestMapping("/buyer/modify")
    public String formUi(
        @RequestParam("what") String buyerId,
        Model model
    ) {
        BuyerDto buyer = service.readBuyer(buyerId);
        model.addAttribute("buyer", buyer);
        return "buyer/buyerForm";
    }

    @RequestMapping(value = "/buyer/modify", method = RequestMethod.POST)
    public String formDataProcess(
        @Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerDto commandObject,
        Map<String, List<String>> errors,
        Model model,
        Authentication authentication

    ) {
        log.info("POST 요청 도착");
        String lvn = null;
        if(errors.isEmpty()) {
            boolean result = service.modifyBuyer(commandObject);
            if (result) {
                lvn = "redirect:/buyer/detail?what="+commandObject.getBuyerId();
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
