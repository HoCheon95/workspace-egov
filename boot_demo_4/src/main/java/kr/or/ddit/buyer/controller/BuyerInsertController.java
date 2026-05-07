package kr.or.ddit.buyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.dto.BuyerDto;

import kr.or.ddit.validate.groups.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/buyer/insert")
public class BuyerInsertController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping
    public String formUi(Model model) {
        model.addAttribute("formType", "insert");
        String lvn = "buyer/buyerForm";
        return lvn;
    }

    @PostMapping
    public String formDataProcess(
            @Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerDto buyerDto, Errors errors,
            Model model) {
        log.info("요청 들어옴");



        model.addAttribute("errorMsg", errors.getAllErrors().toArray());

        String lvn = "";
        if (!errors.hasErrors()) { // 검증 통과
            try {
                boolean created = buyerService.createBuyer(buyerDto);
                if (!created) {
                    lvn = "buyer/buyerForm";
                } else {
                    lvn = "redirect:/buyer/detail?id=%s".formatted(buyerDto.getBuyerId());
                }
            } catch (Exception e) {
                log.error("등록 과정에서 예외 발생", e);
                lvn = "buyer/buyerForm";
            }
        } else {
            lvn = "buyer/buyerForm";
        }

        return lvn;
    }
}
