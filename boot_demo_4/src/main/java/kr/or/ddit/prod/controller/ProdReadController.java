package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.renderer.DefaultPaginationRenderer;
import kr.or.ddit.common.paging.renderer.PaginationRenderer;
import kr.or.ddit.common.paging.search.SimpleCondition;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.dto.LprodDto;
import kr.or.ddit.dto.ProdDto;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.prod.service.ProdService;

// /prod/list
// /prod/detail?id=

@Controller
public class ProdReadController {
    @Autowired
    private ProdService prodService;
    @Autowired
    private LprodService lprodService;
    @Autowired
    private BuyerService buyerService;


    @GetMapping("/prod/list")
    public String prodListView(Model model,
            @RequestParam(name = "page", defaultValue = "1", required = false) int currentPage,
            // @ModelAttribute("search") SimpleCondition simpleCondition
            @ModelAttribute("detailSearch") ProdDto detailCondition,
            @RequestParam Map<String, Object> variousCondition
        ) {

        PaginationInfo<ProdDto> paginationInfo = new PaginationInfo<>(5, 3);
        paginationInfo.setCurrentPage(currentPage);
        // paginationInfo.setSimpleCondition(simpleCondition);
        paginationInfo.setDetailCondition(detailCondition);
        paginationInfo.setVariousCondition(variousCondition);


        List<ProdDto> prodList = prodService.readProdList(paginationInfo);
        List<LprodDto> lprodList = lprodService.readLprodList(paginationInfo);
        List<BuyerDto> buyerList = buyerService.readBuyerListset(paginationInfo);

        PaginationRenderer renderer = new DefaultPaginationRenderer();
        String pagingHtml = renderer.renderPagination(paginationInfo, "fnPaging");

        model.addAttribute("prodList", prodList);
        model.addAttribute("lprodList", lprodList);
        model.addAttribute("buyerList", buyerList);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("pagingHtml", pagingHtml);
        return "prod/prodList";
    }

    @GetMapping("/prod/detail")
    public String prodDetail(@RequestParam("id") String prodId, Model model) {
        ProdDto prodDto = prodService.readProd(prodId);
        model.addAttribute("prod", prodDto);

        return "prod/prodDetail";

    }
}
