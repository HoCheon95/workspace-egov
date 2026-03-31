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

}
