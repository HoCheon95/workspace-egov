package kr.or.ddit.conf;

import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.Cookie;

@Configuration
@EnableWebSecurity
public class SpringSecurityRestConfig {
    @Value("${jwt.secret-key}")
    private byte[] secretKey;

    @Bean
    public JwtDecoder JwtDecoder() {
        SecretKey key = new SecretKeySpec(secretKey, MacAlgorithm.HS256.getName());

        return NimbusJwtDecoder.withSecretKey(key)
                                .macAlgorithm(MacAlgorithm.HS256)
                                .build();
    }

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> 
            authorize.anyRequest().hasRole("ADMIN")
        )
        .cors(cors->cors.configurationSource(corsConfigurationSource))
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(oauth2resourceserver -> 
            oauth2resourceserver.jwt(Customizer.withDefaults())
                                .bearerTokenResolver(req->{
                                    Cookie[] cookies = req.getCookies();
                                    if(cookies == null || cookies.length == 0) return null;

                                    String token =Arrays.stream(cookies)
                                                .filter(sc -> sc.getName().equals("access-token"))
                                                .findAny()
                                                .map(Cookie::getValue)
                                                .orElse(null);
                                    return token;
                                })
        )
        ;

        return http.build();
    }
}
