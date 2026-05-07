package kr.or.ddit.lprod.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.or.ddit.dto.LprodDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class LprodMapperTest {

    @Autowired
    LprodMapper lprodMapper;

    @Test
    void test1() {
        List<LprodDto> lprodDtos = lprodMapper.selectLprodList();
        log.info("{}", lprodDtos.size());
    }

}
