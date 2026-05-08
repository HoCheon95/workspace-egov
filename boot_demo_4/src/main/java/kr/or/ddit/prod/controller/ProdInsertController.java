package kr.or.ddit.prod.controller;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.or.ddit.dto.ProdDto;

@Controller
@Slf4j
@RequestMapping("/prod/create")
public class ProdInsertController {

    @Autowired
    private ProdService prodService;

    @GetMapping
    public String prodCreateForm(Model model) {
        model.addAttribute("formType", "insert");
        return "prod/prodForm";
    }

    @PostMapping
    public String prodCreate(
            @Validated(InsertGroup.class) @ModelAttribute("prod") ProdDto prodDto,
            Errors errors,
            Model model
        ) {
            model.addAttribute("formType", "insert");

            if (errors.hasErrors()) {
                return "prod/prodForm";
            }

            boolean result = prodService.createProd(prodDto);
            if (result) {
                return "redirect:/prod/detail?id=%s".formatted(prodDto.getProdId());
            } else {
                model.addAttribute("errors", "상품 추가가 정상적으로 완료되지 않았습니다. 다시 시도해 주세요.");
                return "prod/prodForm";
            }
    }
}
