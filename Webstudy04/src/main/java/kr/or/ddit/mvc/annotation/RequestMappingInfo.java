package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestMappingInfo {
    private final RequestMappingCondition mappingCondition;
    private final Object controllerInstance;
    private final Method handlerMethod;
    public RequestMappingInfo(RequestMappingCondition mappingCondition, Object controllerInstance, Method handlerMethod) {
        super();
        this.mappingCondition = mappingCondition;
        this.controllerInstance = controllerInstance;
        this.handlerMethod = handlerMethod;
    }
}
