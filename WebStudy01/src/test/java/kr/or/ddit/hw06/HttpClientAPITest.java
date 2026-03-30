package kr.or.ddit.hw06;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        Document doc = Jsoup.parse(respBody);
        Elements rows = doc.select("tbody tr");
        String cols = rows.select("td").get(1).text();
        System.out.println(cols);
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
