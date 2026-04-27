package kr.or.ddit.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import kr.or.ddit.di.ApplicationContextHolder;
import kr.or.ddit.di.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        String basePackage = "kr.or.ddit";
        ApplicationContext context = new ApplicationContext(basePackage);
        ApplicationContextHolder.setContext(context);
        log.info("서버 구동 완료, DI Container 객체 성성");
    }

    public void contextDestoryed(ServletContextEvent sce) {
        log.info("서버 종료");
    }

}
