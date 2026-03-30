package kr.or.ddit.hw06;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw06/proxy-headers")
public class ProxyHeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = request.getParameter("target");
        if (target == null || target.isBlank()) {
            response.sendError(400, "필수 파라미터 누락");
            return;
        }
        URI targetURI = null;
        try {
            targetURI = new URI(target);
        } catch (IllegalArgumentException | URISyntaxException e) {
            response.sendError(400, "target URI 형식 오류");
            return;
        }

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder()
                    .GET()
                    .uri(targetURI)
                    .header("accept", "text/html")
                    .build();
            HttpResponse<String> resp = httpClient.send(req, BodyHandlers.ofString());
            String respBody = resp.body();
            response.getWriter().print(respBody);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // 환율 정보를 가져오는 전용 유틸리티 클래스 🔴
    public class ExchangeRateParser {
        public static double getRealTimeRate(String targetUrl) {
            try {
                // Jsoup을 사용하여 대상 URL의 HTML을 가져온다. 🔴
                org.jsoup.nodes.Document doc = org.jsoup.Jsoup.connect(targetUrl).get();
                // tbody의 첫 번째 행, 두 번째 열에서 환율 문자열을 추출한다. 🔴
                String rateStr = doc.select("tbody tr").get(0).select("td").get(1).text();

                // 쉼표 등 숫자 외의 문자를 제거하고 double로 변환한다. 🔴
                return Double.parseDouble(rateStr.replaceAll("[^0-9.]", ""));
            } catch (Exception e) {
                // 파싱 실패 시 기본값(예: 1500)을 반환하거나 예외를 던진다. 🔴
                return 1500.0;
            }
        }
    }

}
