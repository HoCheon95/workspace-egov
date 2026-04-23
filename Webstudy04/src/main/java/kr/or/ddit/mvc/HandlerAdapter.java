package kr.or.ddit.mvc;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 낱개의 요청을 처리할 수 있는 핸들러를 직접 실행함
 * 
 */
public interface HandlerAdapter {
    /**
     * 낱개의 요청을 처리할 컨트롤러 실행
     * @param <T>
     * @param controllerInfo
     * @param req
     * @param resp
     * @return 컨트롤러에서 결정된 logical view name
     * @throws ServletException
     * @throws IOException
     */
    <T> String invokeHandler(T controllerInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
