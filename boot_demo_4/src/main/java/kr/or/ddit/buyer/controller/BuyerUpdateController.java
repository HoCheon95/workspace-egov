package kr.or.ddit.buyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/buyer/update")
public class BuyerUpdateController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping
    public String formUi(@RequestParam("id") String id, Model model) {
        BuyerDto buyerDto = buyerService.readBuyer(id);
        model.addAttribute("buyer", buyerDto);
        model.addAttribute("formType", "update");
        String lvn = "buyer/buyerForm";
        return lvn;
    }

    @PostMapping
    public String updateBuyer(
            @Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerDto buyerDto,
            BindingResult errors, Model model) {
        String lvn = null;

        if (errors.hasErrors()) {
            lvn = "buyer/buyerForm";
        } else {
            boolean result = buyerService.modifyBuyer(buyerDto);
            if (result) {
                lvn = "redirect:/buyer/detail?id=%s".formatted(buyerDto.getBuyerId());
            } else {
                model.addAttribute("message", "업데이트가 정상적으로 수행되지 않았습니다. 다시 시도해 주세요.");
                lvn = "buyer/buyerForm";
            }
        }

        return lvn;
    }

}
