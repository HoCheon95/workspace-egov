package kr.or.ddit.lprod.controller;

import org.springframework.web.bind.annotation.RestController;
import kr.or.ddit.dto.LprodDto;
import kr.or.ddit.lprod.mapper.LprodMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class LprodRestController {
    @Autowired
    LprodMapper lprodMapper;

    @GetMapping("/rest/lprods")
    public List<LprodDto> getLprodList() {
        return lprodMapper.selectLprodList();
    }

}
