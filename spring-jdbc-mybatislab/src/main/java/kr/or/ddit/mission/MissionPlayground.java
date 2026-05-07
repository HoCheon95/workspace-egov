package kr.or.ddit.mission;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.mission.objs.Barrack;
import kr.or.ddit.mission.objs.TerranMarine;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MissionPlayground {
    public static void main(String[] args) {
        try(
            ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("kr.or.ddit.mission");
        ){
            context.registerShutdownHook();

            Barrack barrack = context.getBean(Barrack.class);
            log.info("{}", barrack.getMarines());

            TerranMarine m1 =  barrack.generateMarine();
            TerranMarine m2 =  barrack.generateMarine();
            TerranMarine m3 =  barrack.generateMarine();
            log.info("{}, {}, {}", m1, m2, m3);

            log.info("{}", barrack.getMarines());

        }
    }
}
