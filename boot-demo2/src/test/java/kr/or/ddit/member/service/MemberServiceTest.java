package kr.or.ddit.member.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testDummyController() throws JsonProcessingException {
        List<MemberDto> list = service.readMemberList();
        String json = mapper.writeValueAsString(list);
        log.info("{}", json);
    }

    @Test
    void test() {
        service.readMemberList().forEach(dto -> log.info("{}", dto));
    }
}
