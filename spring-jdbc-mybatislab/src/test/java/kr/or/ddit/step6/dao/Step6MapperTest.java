package kr.or.ddit.step6.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.step6.conf.Step6JavaConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(classes = Step6JavaConfig.class)
public class Step6MapperTest {
    @Autowired
    Step6Mapper mapper;

    @Test
    void testSelectDataBaseProperties() {
        mapper.selectDataBaseProperties().forEach((dto -> log.info("{}", dto)));
    }
}
