package kr.or.ddit.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Spring Security 주요 객체
 * 1. UserDetails, User (principal) : 한사람의 사용자를 표현하는 객체
 * 2. WebDetails (details) : 사용자의 세션 아이디와 IP 를 저장한 객체
 * 3. GrantedAuthority (authrorities) : 사용자에 부여된 role 이나 인가 정보를 표현하는 객체
 * ==> Authentication (interface) 객체가 모든 프로퍼티를 캡슐화함.
 *  UsernamePasswordAuthenticationToken(세션 기반 인증), BasicAuthenticationToken, JwtAuthenticationToken(헤더 기반 인증)
 * 
 * 4. UserDetailsService(interface) : 한 사람의 사용자 정보를 리포지토리에서 가져오는 객체
 * 5. PasswordEncoder : 비밀번호를 대상으로 단방향 암호화를 지원하는 객체
 * 6. AuthenticationManager : 2Fa 이상의 구조에서 여러 Provider 가 사용되는 경우, 해당 Provider 들을 관리해 최종 인증 여부를 판단하는 객체
 *          AuthenticationProvider : 사용자가 입력한 username 과 password 를 이용해 실제 인증을 처리하는 객체
 * 
 */
@Configuration
@EnableWebSecurity
public class SpringSecurtyConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails a001 = User.withDefaultPasswordEncoder()
            .username("a001")
            .password("java")
            .authorities("ROLE_ADMIN", "ROLD_USER")
            .build();
        UserDetails b001 = User.withDefaultPasswordEncoder()
            .username("b001")
            .password("java")
            .authorities("ROLD_USER")
            .build();

        return new InMemoryUserDetailsManager(a001, b001);
    }
}
