package kr.or.ddit.step2;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.step2.conf.Step2JavaConfig;
import kr.or.ddit.step2.obj.Foo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Step2Playground {
    public static void main(String[] args) {
        try (
            ConfigurableApplicationContext context = 
            // new GenericXmlApplicationContext("classpath:kr/or/ddit/step2/conf/Step2XmlConfig.xml")
            new GenericXmlApplicationContext("classpath:kr/or/ddit/step2/conf/Step2XmlConfigComponentScan.xml")
            // new AnnotationConfigApplicationContext(Step2JavaConfig.class)
        ) {
            context.registerShutdownHook();
            Foo foo1 = context.getBean("foo", Foo.class);
            Foo foo2 = context.getBean("foo", Foo.class);
            log.info("{}", foo1);
            log.info("{}", foo2);
            log.info("foo1 == foo2 : {}", foo1 == foo2);
        }
    }
}
