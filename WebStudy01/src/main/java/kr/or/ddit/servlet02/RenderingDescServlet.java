package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
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
