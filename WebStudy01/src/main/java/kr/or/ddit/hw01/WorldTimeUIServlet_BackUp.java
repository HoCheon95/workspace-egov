package kr.or.ddit.hw01;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw01/worldtime/ui2")
public class WorldTimeUIServlet_BackUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 데이터 수집
        // 2. Locale[] -> localeMap, Set<String> -> zoneMap
        // 3. map -> option 생성
        // 4. 컨텐츠(HTML) 생성

        // 1. 타임존 데이터 수집 및 표시용 이름 생성
        // 시스템에서 사용 가능한 모든 타임존 ID들을 가져옴
        Set<String>zoneSet = ZoneId.getAvailableZoneIds();
        // 타임존 ID를 키로, 표시용 이름을 값으로 하는 맵 생성
        Map<String, String> zoneMap = new HashMap<>();
        // 각 타임존 ID에 대해 표시용 이름 생성
        for(String singleZone : zoneSet){
            // 타임존 ID로부터 ZoneId 객체 생성
            ZoneId tz = ZoneId.of(singleZone);
            // 프랑스어 캐나다 로케일로 타임존의 전체 표시 이름 가져오기
            String name = tz.getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH);
            // 맵에 타임존 ID와 표시용 이름 저장
            zoneMap.put(singleZone, name);
        }
       
        // 2. 로케일 데이터 수집 및 표시용 이름 생성
        // 시스템에서 사용 가능한 모든 로케일 객체들을 가져옴
        Locale[] locales = Locale.getAvailableLocales();
        // 로케일의 언어 태그를 키로, 표시용 이름을 값으로 하는 맵 생성
        Map<String, String> localeMap = new HashMap<>();
        // 각 로케일에 대해 표시용 이름 생성
        for (Locale locale : locales) {
            // 로케일로부터 BCP 47 언어 태그 생성 (예: ko-KR, en-US)
            String languageTag = locale.toLanguageTag();
            // 해당 로케일로 표시한 로케일 이름 가져오기 (자체 표시)
            String name = locale.getDisplayName(locale);
            // 이름이 빈 문자열이면 건너뜀 (유효하지 않은 로케일 필터링)
            if(name.isBlank()) continue;
            // 맵에 언어 태그와 표시용 이름 저장
            localeMap.put(languageTag, name);
        }

        // 3. 타임존 Map을 HTML 옵션 태그로 변환
        // 타임존 옵션들을 담을 StringBuilder 생성 (메모리 효율적)
        StringBuilder zoneOptions = new StringBuilder();
        // zoneMap의 각 엔트리를 순회하며 HTML 옵션 태그 생성
        for (Map.Entry<String, String> entry : zoneMap.entrySet()) {
            // <option value="타임존ID">표시이름</option> 형식으로 HTML 생성
            zoneOptions.append(String.format("<option value=\"%s\">%s</option>\n", entry.getKey(), entry.getValue()));
        }

        // 로케일 데이터도 마찬가지로 <option> 태그로 변환해야 한다.
        StringBuilder localeOptions = new StringBuilder();
        for (Map.Entry<String, String> entry : localeMap.entrySet()) {
            localeOptions.append(String.format("<option value=\"%s\">%s</option>\n", entry.getKey(), entry.getValue()));
        }

        String template = """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <title>World Time Service</title>
            </head>
            <body>
                <h1>세계 시간 서비스</h1>
                <form method="GET" action="../../../hw01/worldtime">
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

        // 맵 객체 대신 생성된 option 문자열을 전달해야 하며, 템플릿 순서에 맞게 locale, timezone 순서로 넣어야 한다.
        String content = String.format(template, localeOptions.toString(), zoneOptions.toString());
        
        // 5. 응답 전송
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(content);
        
    }
}
