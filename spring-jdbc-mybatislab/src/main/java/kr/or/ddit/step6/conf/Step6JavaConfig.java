package kr.or.ddit.step6.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;


/**
 * 스프링 컨테이너의 property source 내에는 시스템 프로퍼티들과 환경 변수들이 포함되어 있음.
 * 커스텀 프로퍼티를 추가할때 키가겹치지 않아야 함.
 */
@ComponentScan(basePackages = "kr.or.ddit.step6") // 스캔할 패키지
@Configuration // 설정파일
@PropertySource("classpath:kr/or/ddit/db/DbInfo.properties") // 커스텀 프로퍼티 
public class Step6JavaConfig {
    @Bean("dataSource")
    public HikariDataSource dataSource(
        @Value("${maindb.url}") String url,
        @Value("${maindb.username}") String username,
        @Value("${maindb.password}") String password,
        @Value("${maxPoolSize}") int maxPoolSize,
        @Value("${minIdle}") int minIdle,
        @Value("${connectionTimeoutMs}") long connectionTimeoutMs
    ) {
        HikariDataSource hds = new HikariDataSource();
        hds.setJdbcUrl(url);
        hds.setUsername(username);
        hds.setPassword(password);
        hds.setMaximumPoolSize(maxPoolSize);
        hds.setMinimumIdle(minIdle);
        hds.setConnectionTimeout(connectionTimeoutMs);
        return hds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate tmpl = new JdbcTemplate(dataSource);
        return tmpl;
    }
}
