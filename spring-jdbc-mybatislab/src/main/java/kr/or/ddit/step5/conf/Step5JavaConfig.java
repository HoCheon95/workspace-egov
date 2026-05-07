package kr.or.ddit.step5.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import kr.or.ddit.step5.objs.DbInfoDto;

/**
 * 주입에 활용되는 어노테이션
 * @Autowired : 타입을 기준으로한 참조 주입
 * @Resource : name 을 기준으로한 참조 투입
 * @Value : spEl(#{beanId.propertyName}), placeholder(${}) 를 기준으로 한 값의 주입
 */
@ComponentScan(basePackages = "kr.or.ddit.step5")
@Configuration
@PropertySource("classpath:kr/or/ddit/db/DbInfo.properties")
public class Step5JavaConfig {

    @Bean
    public PropertiesFactoryBean dbInfo(
        @Value("classpath:kr/or/ddit/db/DbInfo.properties") Resource location
    ) {
        PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocation(location);
        return factory;
    }
    
    @Bean
    public DbInfoDto dbInfoDtoPlaceHolder(
        @Value("${maindb.driverClassName}") String driverClassName,
        @Value("${maindb.url}") String url,
        @Value("${minIdle}") int minIdle
    ) {
        return DbInfoDto.builder()
                        .driverClassName(driverClassName)
                        .url(url)
                        .minIdle(minIdle)
                        .build();
    }

    @Bean
    public DbInfoDto dbInfoDtoSpEl(
        @Value("#{dbInfo['maindb.driverClassName']}") String driverClassName,
        @Value("#{dbInfo['maindb.url']}") String url,
        @Value("#{dbInfo.minIdle}") int minIdle
    ) {
        return DbInfoDto.builder()
                        .driverClassName(driverClassName)
                        .url(url)
                        .minIdle(minIdle)
                        .build();
    }
}
