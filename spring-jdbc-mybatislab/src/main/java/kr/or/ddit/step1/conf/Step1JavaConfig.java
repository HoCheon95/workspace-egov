package kr.or.ddit.step1.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.step1.controller.DummyController;
import kr.or.ddit.step1.dao.DummyDaoMysql;
import kr.or.ddit.step1.dao.DummyDaoOracle;
import kr.or.ddit.step1.dao.IDummyDao;
import kr.or.ddit.step1.service.DummyService;

@Configuration
public class Step1JavaConfig {
    @Bean
    public IDummyDao daoOracle() {
        return new DummyDaoOracle();
    }

    @Bean
    public IDummyDao daoMysql() {
        return new DummyDaoMysql();
    }

    @Bean
    public DummyService dummyService() {
        return new DummyService(daoMysql());
    }

    @Bean
    public DummyController DummyController() {
        return new DummyController((dummyService()));
    }
}
