package kr.or.ddit.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    // Mock : 테스트 목적으로 생성해 사용하는 가짜 객체

    @WithUserDetails(value = "a001", userDetailsServiceBeanName = "customUserDetailsService")
    @Test
    void testGenerateToken() {
        Authentication authentication = SecurityContextHolder. getContext().getAuthentication();
        String token = jwtProvider.generateToken(authentication);

        log.info(token);
    }
}
