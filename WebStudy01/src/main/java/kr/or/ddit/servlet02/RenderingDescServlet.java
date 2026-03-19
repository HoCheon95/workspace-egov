package kr.or.ddit.servlet02;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 웹 어플리케이션에서 사용자가 소비할 최종 UI 컨텐츠가 완성되는 엔드포인트에 따라 2가지 랜더링 구조가 있음.
 * Server Side Rendering : UI 컨텐츠가 서버 사이드에서 완성됨.
 *      UI 컨텐츠 => 템플릿 + 데이터 (서버 사이드)
 * 
 *      Model1 vs Model2 : 책임 분리 여부에 따른 아키텍처
 *      Model2 구조란?
 *          : request 와 response 를 별개의 객체로 분리 처리하는 구조.
 *          request 처리  (controller, 서블릿) : 요청 수신 및 검증, 데이터 생성
 *                                     : 뷰로 전달할 데이터를 SCOPE 를 이용함.
 *                                     : 뷰로 혹은 다른 컨트롤러로 이동하기 위한 흐름제어 구문이 필요함.
 *          response 처리 (view, JSP, Thymleaf, Mustach) : 템플릿과 데이터를 결합해 최종 컨텐츠 생성
 *                               : SCOPE 에서 꺼낸 데이터와 템플릿을 결합할 때 일정한 규칙에 따라 결합하고 랜더링하는 도구 => 템플릿 엔진
 *      템플릿 엔진?
 * Client Side Rendering : UI 컨텐츠가 클라이언트 사이드에서 완성됨.
 *      UI 컨텐츠 => 템플릿 + 데이터 (클라이언트 사이드)
 */
@WebServlet("/server-info")
public class RenderingDescServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 데이터 : 서버의 기본 언어와 국가(locale), 기본 타임존
        // 클라이언트의 로캘 정보를 가져온다.
        Locale clientLocale = req.getLocale();
        // 서버의 기본 로캘 정보를 가져온다.
        Locale serverLocale = Locale.getDefault();

        // 서버의 언어 이름을 클라이언트의 로캘 언어로 변환하여 가져온다.
        String language = serverLocale.getDisplayLanguage(clientLocale);
        // 서버의 국가 이름을 클라이언트의 로캘 언어로 변환하여 가져온다.
        String country = serverLocale.getDisplayCountry(clientLocale);

        // 서버의 기본 시간대(Timezone) 정보를 가져온다.
        ZoneId serverTimezone = ZoneId.systemDefault();
        // 시간대 이름을 클라이언트의 로캘에 맞춰 전체 명칭(Full Style)으로 가져온다.
        String timeZone = serverTimezone.getDisplayName(TextStyle.FULL, clientLocale);
        // =================================================================================

        req.setAttribute("language", language);
        req.setAttribute("country", country);
        req.setAttribute("timeZone", timeZone);

        req.getRequestDispatcher("/01/info.jsp").forward(req, resp);
    }
}
