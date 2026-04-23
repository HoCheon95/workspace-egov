package kr.or.ddit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import kr.or.ddit.mvc.simple.AbstractController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassGraphTest {
    @Test
    void test() {
        String pkg = "kr.or.ddit";

        try (ScanResult scanResult = new ClassGraph()
                .verbose() // Log to stderr
                .enableAllInfo() // Scan classes, methods, fields, annotations
                .acceptPackages(pkg) // Scan com.xyz and subpackages (omit to scan all packages)
                .scan()) { // Start the scan
            for (ClassInfo classInfo : scanResult.getClassesImplementing(AbstractController.class)) {
                Constructor<?> constructor = classInfo.getDeclaredConstructorInfo().getFirst().loadClassAndGetConstructor();

                AbstractController controllerInstance = (AbstractController) constructor.newInstance();
                String condition = controllerInstance.mappingCondition();
                log.info("{} : {}", condition, controllerInstance);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
