package kr.or.ddit.prod.controller;

import java.util.List;

import kr.or.ddit.dto.ProdDto;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.RequestParam;
import kr.or.ddit.prod.service.ProdService;

@Controller
public class ProdReadController {

    @Autowired
    private ProdService service;

    @RequestMapping("/prod/list")
    public String prodList(Model model) {
        List<ProdDto> prodList = service.selectProdList();
        model.addAttribute("prodList", prodList);
        return "prod/prodList";
    }

    @RequestMapping("/prod/detail")
    public String prodDetail(@RequestParam("what") String prodId, Model model) {
        ProdDto prod = service.selectProd(prodId);
        model.addAttribute("prod", prod);
        return "prod/prodDetail";
    }
}
