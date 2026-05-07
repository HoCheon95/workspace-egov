package kr.or.ddit.conf;

import javax.sql.DataSource;

import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "kr.or.ddit.**.mapper") // 스캔할 패키지
@PropertySource("classpath:kr/or/ddit/db/DbInfo.properties") // 커스텀 프로퍼티
public class ModelLayerJavaConfig {
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
    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        return configuration;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(
        DataSource dataSource,
        org.apache.ibatis.session.Configuration configuration,
        @Value("classpath:kr/or/ddit/mybatis/mapper/*.xml") Resource... mapplerLocations
    ) {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setConfiguration(configuration);
        factory.setMapperLocations(mapplerLocations);
        factory.setTypeAliasesPackage("kr.or.ddit.**.dto");
        return factory;
    }
}
