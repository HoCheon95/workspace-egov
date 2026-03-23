package kr.or.ddit.hw03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/hw03/worldtime/ui", "/hw03/worldtime/options" })
public class WorldTimeContentNegotiationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 데이터 수집 : JVM 지원 모든 로케일, JVM 지원 모든 시간대
        Locale[] locales = Locale.getAvailableLocales();
        Set<String> zoneSet = ZoneId.getAvailableZoneIds();

        // 2. Locale[] -> localeMap, Set<String> -> zoneMap
        Map<String, String> localeMap = new HashMap<>();
        for (Locale locale : locales) {
            String languageTag = locale.toLanguageTag();
            String name = locale.getDisplayName(locale);
            if (name.isBlank())
                continue;
            localeMap.put(languageTag, name);
        }

        Map<String, String> zoneMap = new HashMap<>();
        for (String singleZone : zoneSet) {
            ZoneId tz = ZoneId.of(singleZone);
            String name = tz.getDisplayName(TextStyle.FULL, req.getLocale());
            zoneMap.put(singleZone, name);
        }

        // 3. model 을 컨텐츠 생성을 위한 뷰쪽으로 전달
        req.setAttribute("localeMap", localeMap);
        req.setAttribute("zoneMap", zoneMap);

        String accept = req.getHeader("accept");

        if(accept.contains("json")){
            handleJson(req, resp);
        } else if (accept.contains("html")) {
            handleHtml(req, resp);
        } else{
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "응답 컨텐츠 형식 협상에 실패함");
        }
        
    }

    private void handleHtml(
        HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/hw03/world-time-cn.jsp").forward(req, resp);
    }

    private void handleJson(
        HttpServletRequest req, HttpServletResponse resp
    ) throws IOException {
        Map<String, Object> target = new HashMap<>();
        Enumeration<String> attrNames = req.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String arrtName = (String) attrNames.nextElement();
            Object arrtValue = req.getAttribute(arrtName);
            target.put(arrtName, arrtValue);
        }
        // 3. 컨텐츠(JSON) 생성

        String json = new Gson().toJson(target);

        // 4. 응답 전송
        String mime = "application/json;charset=UTF-8";
        resp.setContentType(mime);
        try (
                PrintWriter out = resp.getWriter();) {
            out.print(json);
        }
    }
}
