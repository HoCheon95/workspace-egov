package kr.or.ddit.step3.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.step3.dto.SampleDto;


/**
 *      어노테이션 기반의 빈 등록과 주입
 *      1. 빈 등록
 *          @Component
 *          @Service
 *          @Repository
 *          @Controller
 *          @Configuration
 * 
 *          @RestController
 *          @ControllerAdvice
 * 
 *      2. 주입
 *          @Autowired      : bean 의 타입을 기준으로 주입
 *          @Resource(anem) : bean id 를 기준으로 주입
 *          @Value          : 하나의 리터럴 주입에 활용되고, spEL( #{ beanId.prop } ) 과 함께 사용됨
 * 
 */
@ComponentScan(basePackages = "kr.or.ddit.step3")
@Configuration
public class Step3JavaConfig {
    @Bean
    public StringBuffer StringBuffer() {
        return new StringBuffer();
    }

    @Bean("source")
    public SampleDto source() {
        return new SampleDto("TEXTVALUE", 34, false);
    }

    @Bean
    public SampleDto dest1(
        @Value("#{source.prop1}") String prop1, 
        @Value("#{source.prop2}") int prop2, 
        @Value("#{source['prop3']}") boolean prop3
    ) {
        return new SampleDto(prop1, prop2, prop3);
    }

    // 파라미터의 이름은 reflection 에 포함되지 않는다.
    // 단, 컴파일시에 옵션으로 -parameters 옵션을 추가한 경우, 리플렉션에 포함시킬 수 있다
    // 이 경우, name 을 조건으로 주입받을 수도 있음
    @Bean
    public SampleDto dest2(SampleDto source) {
        return new SampleDto(source.getProp1(), source.getProp2(), source.isProp3());
    }
}
