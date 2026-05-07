package kr.or.ddit.step4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링에서 자원에 대한 접근시 활용되는 API
 * Resource, ResourceLoader
 */
@Slf4j
public class Step4Playground {
    public static void main(String[] args){
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
        
        String path4 = "/home/ho/00.medias/backup";
        Path targetFolder = Path.of(path4);
        copyResource(res1, targetFolder);
        copyResource(res2, targetFolder);
        copyResource(res3, targetFolder);
        
    }

    private static void copyResource(Resource source, Path targetFolder) {
        try (
            InputStream is = source.getInputStream();
        ){
            Path target = targetFolder.resolve(source.getFilename());
            Files.copy(is, target);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
