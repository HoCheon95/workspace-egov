package kr.or.ddit.buyer.controller;

import java.util.List;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.ResponseBody;

/**
 * /buyer/list (accept=application/json) 요청에 대한 처리
 */
@Controller
public class BuyerListJsonController {

    @Autowired
    private BuyerService service;

    @RequestMapping("/buyer/list/json")
    @ResponseBody
    public List<BuyerDto> listJson() {
        return service.readBuyerList();
    }
}