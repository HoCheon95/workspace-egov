package kr.or.ddit.step2.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.step2.obj.Bar;
import kr.or.ddit.step2.obj.Baz;
import kr.or.ddit.step2.obj.Foo;

/**
 * DI 컨테이너 사용 단계
 * 1. 컨테이너 메이븐 의존성 추가 spring-context
 * 2. 컨테이너내에 빈을 등록 : @Bean + factory method
 * 3. 등록된 빈들간의 의존성 주입으로 관계 형성
 * 4. 어플리케이션 entry point 에서 컨테이너 객체 생성
 * 5. 컨테이너로부터 필요한 객체를 주입받아 사용(getBean)
 * 
 * DI 컨테이너 빈 관리 특성
 * 1. Eager-loading : @Lazy 설정이 없는한 등록된 모든 빈의 객체를 컨테이너가 초기화될때 일시에 생성함.
 * 2. Singleton : @Scope("prototype") 설정이 없는 한 등록된 빈의 객체는 싱글턴으로 관리됨.
 *              ex) prototype (Lazy) : 빈을 주입받을 때마다 새로운 인스턴스가 생성됨.
 * 3. 생명주기 이벤트를 처리하는 콜백을 정의할 수 있음 : @PostContruct, @PreDestroy (jakarta annotation-api 필요)
 *              @PostContruct 로 지정된 초기화 콜백은 객체가 생성되고 모든 주입이 완료된 후 실행됨.
 * 
 */
// @Configuration
// @Lazy
public class Step2JavaConfig {
    @Bean("bar")
    public Bar bar() {
        return new Bar();
    }

    @Bean
    public Baz baz() {
        return new Baz();
    }
    
    @Bean
    // @Scope("prototype")
    public Foo foo(Bar bar) {
        Foo foo = new Foo(bar);
        foo.setBaz(baz());
        return foo;
    }
}
