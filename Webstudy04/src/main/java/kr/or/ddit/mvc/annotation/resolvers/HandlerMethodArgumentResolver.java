package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 핸들러 어댑터가 핸들러 메소드의 인자를 결정할때 사용할 전략 객체
 */
public interface HandlerMethodArgumentResolver {
    /**
     * 어떤 종류의 인자를현재 resolver 로 해결할 수 있는지를 표현함
     * @param parameter
     * @return
     */
    Boolean supportParameter(Parameter parameter);
    
    /**
     * 파라미터 하나에 대응하는 인자를 생성함
     * @param parameter
     * @param req
     * @param resp
     * @return
     * @throws IOException 
     * @throws ServletException 
     */
    Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
