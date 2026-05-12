package kr.or.ddit.conf;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> siteMeshFilter() {
        Filter siteMeshFilter = new SiteMeshFilterBuilder()
                .setDecoratorPrefix("/WEB-INF/decorators/")
                .setMimeTypes("text/html")
                .addExcludedPath("/rest/**")
                .addExcludedPath("/login")
                .addDecoratorPath("/**", "main-layout.jsp")
                .create();
        FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
        frb.setFilter(siteMeshFilter);
        frb.setOrder(100);
        return frb;

    }
}
