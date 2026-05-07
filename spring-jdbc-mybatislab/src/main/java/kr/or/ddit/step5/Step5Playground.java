package kr.or.ddit.step5;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.step5.objs.DbInfoDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Step5Playground {
    // public static void main(String[] args) {
    //     try(
    //         ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("kr.or.ddit.step5");
    //     ){
    //         context.registerShutdownHook();

    //         // DbInfoDto dbInfoDtoPlaceHolder = context.getBean("dbInfoDtoPlaceHolder", DbInfoDto.class);
    //         // log.info("{}", dbInfoDtoPlaceHolder);
    //         // DbInfoDto dbInfoDtoSpEl = context.getBean("dbInfoDtoSpEl", DbInfoDto.class);
    //         // log.info("{}", dbInfoDtoSpEl);
    //     }
    // }
}
