package kr.or.ddit.conf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void test() {
        String plain = "java";
        String encryptedEncoded = passwordEncoder.encode(plain);
        log.info("encryptedEncoded : {}", encryptedEncoded);
        // BCrypt는 매번 다른 해시값 생성 → matches()로 검증해야 함
        log.info("matches : {}", passwordEncoder.matches(plain, encryptedEncoded));
    }
}
