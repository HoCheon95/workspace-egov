package kr.or.ddit.hw02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@WebServlet("/hw02/worldtime/ui")
public class WorldTimeModel2UIServlet extends HttpServlet {
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

        // ====================================================

        req.setAttribute("localeMap", localeMap);
        req.setAttribute("zoneMap", zoneMap);

        req.getRequestDispatcher("/WEB-INF/views/hw02/world-time-ui.jsp").forward(req, resp);
    }
}