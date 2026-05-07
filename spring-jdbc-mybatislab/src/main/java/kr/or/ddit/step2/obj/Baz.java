package kr.or.ddit.step2.obj;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Baz {
    public Baz() {
        log.info("{} 객체 생성", this.getClass().getName());
    }

    @PostConstruct
    public void start() {
        log.info("{} 초기화 콜백 종료", this.getClass().getName());
    }
    
    @PreDestroy
    public void stop() {
        log.info("{} 객체 소멸", this.getClass().getName());
    }
}
