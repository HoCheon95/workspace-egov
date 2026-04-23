package kr.or.ddit.mvc.simple;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.mvc.HandlerMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractControllerHandlerMapping implements HandlerMapping{

    private Map<String, AbstractController> handlerMap;

    public AbstractControllerHandlerMapping(String pkg) {
        handlerMap = new LinkedHashMap<>();
        // AbstractController controller = new IndexController();
        // handlerMap.put(controller.mappingCondition(), controller);

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
                handlerMap.put(condition, controllerInstance);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T findCommandHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI().substring(req.getContextPath().length()).split(";")[0];
        return (T) handlerMap.get(requestURI);
    }

}
