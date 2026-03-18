package kr.or.ddit.hw01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;

import java.util.Set;

@WebServlet("/hw01/worldtime/options")
public class LocaleTimeZoneJsonServlet extends HttpServlet {
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
            if (name.isBlank()) continue;
            localeMap.put(languageTag, name);
        }

        Map<String, String> zoneMap = new HashMap<>();
        for (String singleZone : zoneSet) {
            ZoneId tz = ZoneId.of(singleZone);
            String name = tz.getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH);
            if(name.contains("−")) continue;
            zoneMap.put(singleZone, name);
        }

        // 3. 컨텐츠(JSON) 생성

        Map<String,Map<String,String>> target = Map.of("locales", localeMap, "timeZones", zoneMap);

        String json = new Gson().toJson(target);

        // 4. 응답 전송
        String mime = "application/json;charset=UTF-8";
        resp.setContentType(mime);
        try(
            PrintWriter out = resp.getWriter();
        ){
            out.print(json);
        }
    }
}