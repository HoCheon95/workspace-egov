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
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.InsertGroup;

@Controller
public class ProdInsertController {
    @Autowired
    private ProdService service;

    @RequestMapping("/prod/create")
    public String prodCreate() {
        return "prod/prodForm";
    }

    @RequestMapping(value = "/prod/create", method = RequestMethod.POST)
    public String prodCreateProcess(
        @Validated(InsertGroup.class) @ModelAttribute("prod") ProdDto prod,
        Map<String, List<String>> errors,
        Model model
    ) {
        String lvn = null;
        if(errors.isEmpty()) {
            boolean result = service.createProd(prod);
            if(result) {
                lvn = "redirect:/prod/detail?what="+prod.getProdId();
            } else {
                model.addAttribute("message", "알수 없는 어류로 등록실패");
                lvn = "prod/prodForm";
            }
        }
        return lvn;
    }
}
