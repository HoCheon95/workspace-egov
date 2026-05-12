package kr.or.ddit.member.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberMapperTest {
    @Autowired
    MemberMapper mapper;

    @Test
    void testSelectMember() {
        MemberDTO dto = mapper.selectMember("a001");
        log.info("{}", dto);
    }
}
