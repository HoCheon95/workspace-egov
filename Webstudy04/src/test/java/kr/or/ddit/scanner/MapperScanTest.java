package kr.or.ddit.scanner;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperScanTest {

    @Test
    void test() {
        Map<Class<?>, Object> beanRegistry = new LinkedHashMap<>();

        String pkg = "kr.or.ddit";
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()        // Scan classes, methods, fields, annotations
                .acceptPackages(pkg)    // Scan com.xyz and subpackages (omit to scan all packages)
                .scan()) {              // Start the scan
            for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(Mapper.class)) {
                if (!classInfo.isInterface()) continue;
                Class<?> mapperType = classInfo.loadClass();
                Object proxy = MapperProxyGenerator.generateMapperProxy(mapperType);
                beanRegistry.put(mapperType, proxy);
                log.info("{} 타입 프록시 {} 생성", mapperType.getName(), proxy);
            }
        }
    }
}
