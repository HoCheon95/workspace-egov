package kr.or.ddit.step3;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.step3.conf.Step3JavaConfig;
import kr.or.ddit.step3.controller.SampleController;
import kr.or.ddit.step3.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Step3Playground {
    // public static void main(String[] args) {
        // try(
        //     ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Step3JavaConfig.class);
        // ){
        //     context.registerShutdownHook();
        //     int count = context.getBeanDefinitionCount();
        //     log.info("bean 개수 : {}", count);
        //     Arrays.stream(context.getBeanDefinitionNames())
        //             .forEach(dn->log.info("{}", dn));
        //     SampleController controller = context.getBean(SampleController.class);
        //     controller.receiveCommand("P101");

        //     SampleDto source =  context.getBean("source",SampleDto.class);
        //     SampleDto dest1 =  context.getBean("dest1",SampleDto.class);
        //     SampleDto dest2 =  context.getBean("dest2",SampleDto.class);
        //     log.info("{}", source);
        //     log.info("{}", dest1);
        //     log.info("{}", dest2);
        // }
    // }
}
