package kr.or.ddit.step7.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.step7.conf.Step7JavaConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(classes = Step7JavaConfig.class)
public class LprodServiceTest {

    @Autowired
    private LprodService service;

    @Test
    void testReadLprodList() {
        service.readLprodList().forEach(dto -> log.info("{}",dto));;
    }
}
