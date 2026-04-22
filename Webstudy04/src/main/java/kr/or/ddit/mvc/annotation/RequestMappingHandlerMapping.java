package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.classgraph.AnnotationInfo;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.mvc.HandlerMapping;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestMappingHandlerMapping implements HandlerMapping{

    private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;

    public RequestMappingHandlerMapping(String pkg) {
        handlerMap = new LinkedHashMap<>();

        try (ScanResult scanResult = new ClassGraph()
                .verbose()              // Log to stderr
                .enableAllInfo()        // Scan classes, methods, fields, annotations
                .acceptPackages(pkg)    // Scan com.xyz and subpackages (omit to scan all packages)
                .scan()) {              // Start the scan
            for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(Controller.class)) {
                
                Constructor<?> constructor = classInfo.getDeclaredConstructorInfo().getFirst().loadClassAndGetConstructor();
                Object controllerInstance = constructor.newInstance();
                
                classInfo.getDeclaredMethodInfo()
                        .stream()
                        .filter(mi -> mi.hasAnnotation(RequestMapping.class))
                        .forEach(mi -> {
                            AnnotationInfo ai = mi.getAnnotationInfo(RequestMapping.class);
                            RequestMapping mappingAnnotation = (RequestMapping) ai.loadClassAndInstantiate();
                            String url = mappingAnnotation.value();
                            RequestMethod method = mappingAnnotation.method();
                            RequestMappingCondition condition = new RequestMappingCondition(url, method);
                            Method handlerMethod = mi.loadClassAndGetMethod();
                            RequestMappingInfo mappingInfo = new RequestMappingInfo(condition, controllerInstance, handlerMethod);
                            handlerMap.put(condition, mappingInfo);
                            log.info("{}", mappingInfo);
                        });
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T findCommandHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI().substring(req.getContextPath().length()).split(";")[0];
        RequestMethod method = RequestMethod.valueOf(req.getMethod().toUpperCase());

        RequestMappingCondition mappingCondition = new RequestMappingCondition(requestURI, method);
        return (T) handlerMap.get(mappingCondition);
    }
}
