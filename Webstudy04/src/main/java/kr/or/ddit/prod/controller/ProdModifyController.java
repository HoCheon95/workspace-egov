package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.ProdDto;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.UpdateGroup;

@Controller
public class ProdModifyController {
    @Autowired
    private ProdService service;

    @RequestMapping("/prod/modify")
    public String prodModify(
        @RequestParam(value = "what") String prodId,
        Model model
    ){
        ProdDto prod = service.selectProd(prodId);
        model.addAttribute("prod", prod);
        return "prod/prodForm";
    }

    @RequestMapping(value = "/prod/modify", method = RequestMethod.POST)
    public String formDataProcess(
        @Validated(UpdateGroup.class) @ModelAttribute("prod") ProdDto prod,
        Map<String, List<String>> errors,
        Model model

    ) {
        String lvn = null;
        if (errors.isEmpty()) {
            boolean result =  service.modifyProd(prod);
            if (result) {
                lvn = "redirect:/prod/detail?what=" + prod.getProdId();
            } else {
                model.addAttribute("message", "수정 실패");
                lvn = "prod/prodForm";
            }
        } else {
            lvn = "prod/prodForm";
        }
        return lvn;
    }
}
