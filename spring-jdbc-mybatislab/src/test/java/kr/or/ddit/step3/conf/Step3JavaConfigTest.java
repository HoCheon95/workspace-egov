package kr.or.ddit.step3.conf;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.annotation.Resource;
import kr.or.ddit.step3.controller.SampleController;
import kr.or.ddit.step3.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(classes = Step3JavaConfig.class)
public class Step3JavaConfigTest {
    @Autowired
    ConfigurableApplicationContext context;

    @Resource(name = "source")
    SampleDto source;
    @Resource(name = "dest1")
    SampleDto dest1;
    @Resource(name = "dest2")
    // @Autowired
    SampleDto dest2;

    @Test
    void test() {
        try(
            ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Step3JavaConfig.class);
        ){
            context.registerShutdownHook();
            int count = context.getBeanDefinitionCount();
            log.info("bean 개수 : {}", count);
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(dn->log.info("{}", dn));
            SampleController controller = context.getBean(SampleController.class);
            controller.receiveCommand("P101");

            // SampleDto source =  context.getBean("source",SampleDto.class);
            // SampleDto dest1 =  context.getBean("dest1",SampleDto.class);
            // SampleDto dest2 =  context.getBean("dest2",SampleDto.class);
            log.info("{}", source);
            log.info("{}", dest1);
            log.info("{}", dest2);
        }
    }
}
