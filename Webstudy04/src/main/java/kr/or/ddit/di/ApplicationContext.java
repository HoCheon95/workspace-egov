package kr.or.ddit.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.function.Failable;
import org.apache.ibatis.annotations.Mapper;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import kr.or.ddit.di.stereotype.Component;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * 객체(bean)를 생성(@Component)하고, 필요시 어플리케이션 내부에 객체를 주입(@Autowired)하는 컨테이너
 */
@Slf4j
public class ApplicationContext {
    private Map<Class<?>, Object> beanRegistry = new LinkedHashMap<>();

    public ApplicationContext(String basePackage) {

        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()        // Scan classes, methods, fields, annotations
                .acceptPackages(basePackage)    // Scan com.xyz and subpackages (omit to scan all packages)
                .scan()) {              // Start the scan
            
            for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(Mapper.class)) {
                if (!classInfo.isInterface()) continue;
                Class<?> mapperType = classInfo.loadClass();
                Object proxy = MapperProxyGenerator.generateMapperProxy(mapperType);
                beanRegistry.put(mapperType, proxy);
                log.info("{} 타입 프록시 {} 생성", mapperType.getName(), proxy);
            }

            for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(Component.class)) {
                if (classInfo.isInterface() || classInfo.isAnnotation()) continue;
                Class<?> beanType = classInfo.loadClass();
                Constructor<?> constructor = beanType.getDeclaredConstructor();

                Object beanInstance = constructor.newInstance();
                beanRegistry.put(beanType, beanInstance);
                log.info("{} 타입 bean 객체 {} 생성", beanType.getName(), beanInstance);
                
                
                // 동일한 구현체를 인터페이스의 bean 으로 등록.
                classInfo.getInterfaces().forEach(ici -> {
                    Class<?> interfaceType = ici.loadClass();
                    beanRegistry.put(interfaceType, beanInstance);
                    log.info("{} 타입 bean 객체 {} 생성", interfaceType.getName(), beanInstance);
                });

                
            }

            beanRegistry.values().forEach(Failable.asConsumer(beanInstance -> inject(beanInstance)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void inject(Object beanInstance) throws IllegalArgumentException, IllegalAccessException {
        Class<?> beanType = beanInstance.getClass();
        for (Field field : beanType.getDeclaredFields()) {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if(annotation == null) continue;

            field.setAccessible(true);
            Class<?> dependencyType =  field.getType();
            Object dependency =  beanRegistry.get(dependencyType);
            if(dependency == null) {
                throw new RuntimeException("주입 실패. $s 타입으로 등록된 bean 객체가 없음.".formatted(dependencyType.getName()));
            }
            field.set(beanInstance, dependency);
            log.info("{} 타입 bean 에 {} 타입 bean 을 주입(Dependency Injection)했음.", beanType.getName(), dependencyType.getName());
        }
    }

    public <T> T getBean(Class<T> beanType) {
        Object beanInstance =  beanRegistry.get(beanType);
        return beanType.cast(beanInstance);
    }
}
