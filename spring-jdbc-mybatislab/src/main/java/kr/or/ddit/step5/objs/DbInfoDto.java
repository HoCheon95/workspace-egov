package kr.or.ddit.step5.objs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Spring 컨테이너에 의해 관리되는 Bean으로 등록한다.
 */
@Slf4j
@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DbInfoDto {
    @Value("${maindb.driverClassName}")
    private String driverClassName;
    @Value("${maindb.url}")
    private String url;
    @Value("${maindb.username}")
    private String username;
    @Value("${maindb.password}")
    private String password;

    @Value("${maxPoolSize}")
    private int maxPoolSize;
    @Value("${minIdle}")
    private int minIdle;
    @Value("${connectionTimeoutMs}")
    private int connectionTimeoutMs;

    @PostConstruct
    public void init() {
        log.info("{}", this);
    }
}
