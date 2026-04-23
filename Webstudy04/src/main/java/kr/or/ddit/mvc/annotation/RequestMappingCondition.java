package kr.or.ddit.mvc.annotation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class RequestMappingCondition {
    private final String url;
    private final RequestMethod method;

    public RequestMappingCondition(String url, RequestMethod method) {
        super();
        this.url = url;
        this.method = method;
    }
}
