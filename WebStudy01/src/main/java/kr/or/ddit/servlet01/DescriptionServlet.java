package kr.or.ddit.servlet01;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 서블릿 스펙
 * 
 *  : 웹상에서 발생한 요청을 수신하고, 일정한 처리를 수행한 후, 동적인 응답을 생성할 수 있는 자바 객체에 대한 명세서.
 *  : 자바 기반의 백엔드 모듈로 자바라는 언어의 특성을 그대로 사용할 수 있고,
 *    확장 CGI 방식의 동작 구조를 갖고있어서 하나의 요청을 하나의 쓰레드로 처리하는 멀티 쓰레딩 구조를 형성할 수 있음.
 * 
 *  개발 단계
 *  1. HTTPservlet 구현체 정의
 *  2. callback 재정의
 *      1) 생명주기 콜백
 *      2) 요청 콜백
 *  3. 서블릿 컨테이너에 등록
 *      1) web.xml
 *      2) annotation
 *  4. 서블릿의 동작 조건이 되는 url 매핑
 *      1) web.xml
 *      2) annotation
 */

public class DescriptionServlet extends HttpServlet{
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println(this.getClass().getName()+"객체 초기화 완료");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service 메소드 동작 시작!");
        super.service(req, resp);
        System.out.println("service 메소드 종료!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(this.getClass().getName()+"객체 소멸");
    }
}
