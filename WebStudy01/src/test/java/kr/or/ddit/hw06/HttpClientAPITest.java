package kr.or.ddit.hw06;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang3.function.Failable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

public class HttpClientAPITest {

    @Test
    void TestExchangeAPI() throws IOException, InterruptedException{
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("https://finance.naver.com/marketindex/exchangeList.naver"))
                        .header("accept", "text/html")
                        .build();
        HttpResponse<String> resp = httpClient.send(req, BodyHandlers.ofString());
        String respBody = resp.body();
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
        Document document = Jsoup.parse(respBody);
        double rate =  document.select(".tit")
                .stream()
                .filter(el->el.text().contains("USD"))
                .findFirst()
                // .map(el->el.nextElementSibling())
                .map(el->el.parent())
                .map(p->p.selectFirst(".sale"))
                .map(s->s.text())
                .map(Failable.asFunction(t->formatter.parse(t).doubleValue()))
                .orElse(1500d);
        System.out.println(rate);


        // Elements rows = doc.select("tbody tr");
        // String cols = rows.select("td").get(1).text();
        // System.out.println(cols);
    }

    @Test
    void Test() throws IOException, InterruptedException{
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("https://developer.mozilla.org/ko/docs/Web/HTTP/Reference/Headers"))
                        .header("accept", "text/html")
                        .build();
        HttpResponse<String> resp = httpClient.send(req, BodyHandlers.ofString());
        String respBody = resp.body();
        System.out.println(respBody);
    }
}
