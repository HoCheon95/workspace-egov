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
 *      1) 생명주기 콜백 : init, destory
 *                  : 컨테이너는 일반적으로 관리 대상인 객체를 싱글턴으로 운영함.
 *      2) 요청 콜백 : service, doXXX
 *                  : 매요청 발생시 service 가 호출되고, doXXX 메소드로 분기가 이루어짐.
 *                  : doXXX 계열의 메소드를 재정의할 때 super.doXXX 코드(405 에러 발생)를 제거해야함.
 *  컨테이너의 의해 서블릿이 관리되므로, 그 관리 정책을 결정할 때 등록 시에 사용되는 설정으로 제어함.
 *      ex) 객체 생성 시점을 결정하는 loadonstartup
 *          lazy-loading : 객체가 사용되는 시점까지 객체의 생성을 미루는 방식
 *          eager-loading : 객체가 사용되기 전에 미리 객체 생성을 해둠
 *      ex) 객체 생성 시점에 전달하는 파라미터들.. params
 *  3. 서블릿 컨테이너에 등록
 *      1) web.xml : servlet -> servlet-name -> serlvet-class
 *      2) annotation
 *  4. 서블릿의 동작 조건이 되는 url 매핑
 *      1) web.xml : servlet-mapping -> servlet-name, url-pattern
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
        System.out.println("doGet 메소드 동작");
    }

    @Override
    public void destroy() {
        System.out.println(this.getClass().getName()+"객체 소멸");
    }
}
