package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;

import kr.or.ddit.dto.LprodDto;
import kr.or.ddit.lprod.mapper.LprodMapper;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.annotation.stereotype.ResponseBody;

/**
 * /lprod/list (accept=application/json) 요청에 대한 처리
 */

@Controller
public class LprodListJsonController {
    
    @Autowired
    private LprodMapper mapper;

    @RequestMapping("/lprod/list")
    @ResponseBody
    public List<LprodDto> listJson() throws IOException {
        return mapper.selectLprodList();
    }
}
