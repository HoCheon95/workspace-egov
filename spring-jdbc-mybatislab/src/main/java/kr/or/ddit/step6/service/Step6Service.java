package kr.or.ddit.step6.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.step6.dao.Step6Dao;
import kr.or.ddit.step6.dto.DataBasePropertyDto;
import lombok.RequiredArgsConstructor;

@Service // 비즈니스 로직
@RequiredArgsConstructor // 생성자 자동 주입
public class Step6Service {
    
    private final Step6Dao dao;

    public List<DataBasePropertyDto> readDataBaseProperties() {
        return dao.selectDataBaseProperties();
    }
}
