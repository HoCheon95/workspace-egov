package com.example.demo.lprod.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LprodDto;
import com.example.demo.lprod.mapper.LprodMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로깅
@Service // 비즈니스 로직
@RequiredArgsConstructor // 생성자 자동 주입
@ToString // 객체 정보 출력
public class LprodService {
    
    private final LprodMapper mapper;
    
    public List<LprodDto> readLprodList() {
        return mapper.selectLprodList();
    }

    @PostConstruct // 객체 생성 후 초기화
    public void init() {
        log.info("{}", this);
    }
}
