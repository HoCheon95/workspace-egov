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
import java.util.Map.Entry;
import java.util.Set;

@WebServlet("/hw01/worldtime/ui")
public class WorldTimeUIServlet extends HttpServlet {
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
            String name = tz.getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH);
            zoneMap.put(singleZone, name);
        }

        // =========================UI 작업=========================

        // 3. map -> option 생성
        StringBuffer localeOptions = new StringBuffer();
        for(Entry<String, String> localeEntry : localeMap.entrySet()) {
            String languageTag = localeEntry.getKey();
            String name = localeEntry.getValue();
            localeOptions.append(
                String.format("<option value='%s'>%s</option>\n", languageTag, name)
            );
        }

        StringBuffer zoneOptions = new StringBuffer();
        for(Entry<String, String> zoneEntry : zoneMap.entrySet()) {
            String id = zoneEntry.getKey();
            String name = zoneEntry.getValue();
            zoneOptions.append(
                String.format("<option value='%s'>%s</option>\n", id, name)
            );
        }
        // 4. 컨텐츠(HTML) 생성

        String template = """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <title>World Time Service</title>
            </head>
            <body>
                <h1>세계 시간 서비스</h1>
                <form method="GET" action="../../hw01/worldtime">
                    <label for="locale">로케일:</label>
                    <select name="locale" id="locale">
                        %s
                    </select>
                    <br><br>
                    <label for="timezone">타임존:</label>
                    <select name="timezone" id="timezone">
                        %s
                    </select>
                    <br><br>
                    <button type="submit">시간 확인(Sync)</button>
                </form>
                <div id="result"></div>
            </body>
            </html>
        """;

        String content = String.format(template, localeOptions, zoneOptions);

        // 5. 응답 전송
        String mime = "text/html;charset=UTF-8";
        resp.setContentType(mime);
        try(
            PrintWriter out = resp.getWriter();
        ){
            out.print(content);
        }
    }
}