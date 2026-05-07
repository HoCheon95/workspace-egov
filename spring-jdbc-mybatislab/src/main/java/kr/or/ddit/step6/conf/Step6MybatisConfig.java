package kr.or.ddit.step6.conf;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration // 설정파일
@MapperScan(basePackages = "kr.or.ddit.*.dao") // 스캔할 패키지
public class Step6MybatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(
            DataSource DataSource,
            @Value("classpath:kr/or/ddit/mybatis/Configuration.xml") Resource configLocation,
            @Value("classpath:kr/or/ddit/mybatis/mappers/*.xml") Resource...mapplerLocations
        ) {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(DataSource);
        factory.setConfigLocation(configLocation);
        factory.setMapperLocations(mapplerLocations);
        return factory;
    }
}
