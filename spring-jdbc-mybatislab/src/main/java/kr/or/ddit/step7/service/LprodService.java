package kr.or.ddit.step7.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.step7.dto.LprodDto;
import kr.or.ddit.step7.mapper.LprodMapper;
import lombok.RequiredArgsConstructor;

@Service // 비즈니스 로직
@RequiredArgsConstructor // 생성자 자동 주입
public class LprodService {
    
    private final LprodMapper mapper;
    
    public List<LprodDto> readLprodList() {
        return mapper.selectLprodList();
    }
}
