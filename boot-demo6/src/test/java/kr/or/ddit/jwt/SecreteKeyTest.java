package kr.or.ddit.jwt;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SecreteKeyTest {

    @Test
    void test() {
        // 256 bit 의 키를 생성
        String secretKey = RandomStringUtils.secureStrong().nextAlphanumeric(256/8);
        log.info("key : {}", secretKey);
    }
}
