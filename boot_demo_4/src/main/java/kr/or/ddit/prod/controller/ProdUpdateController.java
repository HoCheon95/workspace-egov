package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.or.ddit.dto.ProdDto;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/prod/edit")
public class ProdUpdateController {
    @Autowired
    private ProdService prodService;

    @GetMapping
    public String prodCreateForm(@RequestParam("id") String prodID, Model model) {
        ProdDto prodDto = prodService.readProd(prodID);
        model.addAttribute("prod", prodDto);
        model.addAttribute("formType", "update");
        return "prod/prodForm";
    }

    @PostMapping
    public String prodCreate(@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdDto prodDto,
            Map<String, List<String>> errors, Model model) {
        log.info("{}", prodDto);
        model.addAttribute("formType", "update");

        if (!errors.isEmpty()) {
            return "prod/prodForm";
        }

        boolean result = prodService.modifyProd(prodDto);
        if (result) {
            return "redirect:/prod/list";
        } else {
            return "prod/prodForm";
        }
    }
}
