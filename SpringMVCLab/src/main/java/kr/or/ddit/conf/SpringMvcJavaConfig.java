package kr.or.ddit.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
public class SpringMvcJavaConfig implements WebMvcConfigurer{

    @Bean
    public InternalResourceViewResolver irvr() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
        return vr;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/");
    }
    @PostConstruct
    public void init() {
        log.info("{} 객체 초기화 완료", this);
    }

}
