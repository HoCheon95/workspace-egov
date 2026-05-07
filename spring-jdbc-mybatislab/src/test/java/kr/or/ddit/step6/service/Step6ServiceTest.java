package kr.or.ddit.step6.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.step6.conf.Step6JavaConfig;
import kr.or.ddit.step6.dto.DataBasePropertyDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(classes = {Step6JavaConfig.class})
public class Step6ServiceTest {
    @Autowired
    Step6Service service;

    @Test
    void testReadDataBaseProperties() {
        List<DataBasePropertyDto> list =  service.readDataBaseProperties();
        list.forEach(dto -> log.info("{}", dto));
    }
}
