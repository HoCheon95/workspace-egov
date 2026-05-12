package kr.or.ddit.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import kr.or.ddit.security.users.CustomUserDetailsService;

import jakarta.servlet.DispatcherType;

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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        .csrf(Customizer.withDefaults())
        .authorizeHttpRequests(authorize -> 
            authorize
                .dispatcherTypeMatchers(
                    DispatcherType.FORWARD, 
                    DispatcherType.INCLUDE,
                    DispatcherType.ERROR
                ).permitAll()
                .requestMatchers(HttpMethod.GET, "/prod/**").permitAll()
                .requestMatchers("/prod/**").hasRole("ADMIN")
                .requestMatchers("/member/regist").permitAll()
                .requestMatchers("/member/**").authenticated()
                .requestMatchers("/buyer/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
        )
        // .httpBasic(Customizer.withDefaults()) // 헤더 기반 인증, username/password 노출 위험 => Bearer(JWT) 구조로 변경
        .formLogin(login->
            login.loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
        ) // 세션 기반 인증 
        .logout(logout->
            logout.logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("dummy")
        )
        ;

        return http.build();
    } 

    @Bean
    public LogoutHandler logoutHandler() {
        CookieClearingLogoutHandler handler1 = new CookieClearingLogoutHandler("dummy");
        SecurityContextLogoutHandler handler2 = new SecurityContextLogoutHandler();
        handler2.setInvalidateHttpSession(true);
        CompositeLogoutHandler logoutHandler = new CompositeLogoutHandler(handler1, handler2);
        return logoutHandler;
    }
}
