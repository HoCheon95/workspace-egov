package kr.or.ddit.step1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.step1.conf.Step1JavaConfig;
import kr.or.ddit.step1.controller.DummyController;

public class Step1Playground {
    public static void main(String[] args) {
        try(
            ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Step1JavaConfig.class);
        ){
            DummyController controller = context.getBean(DummyController.class);
            controller.receiveCommand();
        }

    }
}
