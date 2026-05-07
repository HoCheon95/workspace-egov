package kr.or.ddit.step2.obj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 의존성 주입(dependency injection) 방법
 * 
 * 1. 생성자 주입(constructor injection)
 * 2. 세터 주입(setter injection)
 * 
 */
@ToString
@Slf4j
@Component
@Scope("prototype")
public class Foo {
    private final Bar bar; //필수 전략
    private Baz baz; // 옵셔널

    public Foo(Bar bar) {
        super();
        this.bar = bar;
        log.info("{} 객체 생성, {} 를 생성자로 주입받음", this.getClass().getName(), bar.getClass().getName());
    }
    @Autowired
    public void setBaz(Baz baz){
        this.baz = baz;
        log.info("{} 객체 생성, {} 를 setter 로 주입받음", this.getClass().getName(), baz.getClass().getName());
    }

    @PostConstruct
    public void start() {
        log.info("{} 초기화 콜백 종료, {}, {} 주입 여부 확인", this.getClass().getName(), bar, baz);
    }
    
    @PreDestroy
    public void stop() {
        log.info("{} 객체 소멸", this.getClass().getName());
    }
    
}
