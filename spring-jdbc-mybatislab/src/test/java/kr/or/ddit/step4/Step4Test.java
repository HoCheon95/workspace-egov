package kr.or.ddit.step4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig
public class Step4Test {

    @Autowired
    ResourceLoader loader;

    @Test
    void test() {
        ResourceLoader loader = new AnnotationConfigApplicationContext();
        String path1 = "file:/home/ho/00.medias/images/cat1.jpg";
        String path2 = "classpath:spy.properties";
        String path3 = "https://dummyimage.com/600x400/000/fff";
        Resource res1 = loader.getResource(path1);
        Resource res2 = loader.getResource(path2);
        Resource res3 = loader.getResource(path3);

        log.info("{}", res1.getClass().getName());
        log.info("{}", res2.getClass().getName());
        log.info("{}", res3.getClass().getName());
    }
}
