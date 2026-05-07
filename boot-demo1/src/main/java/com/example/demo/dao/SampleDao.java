package com.example.demo.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로깅
@Repository // DB접근
public class SampleDao {

    @Autowired // bean 의 타입을 기준으로 주입
    private DataSource dataSource;

    @Autowired // bean 의 타입을 기준으로 주입
    private JdbcTemplate jdbcTemplate;

    @PostConstruct // 객체 생성 후 초기화
    public void init() {
        log.info("{} 객체 생성 후 {} 객체 주입받고 초기화 완료", this, jdbcTemplate);
    }
}
