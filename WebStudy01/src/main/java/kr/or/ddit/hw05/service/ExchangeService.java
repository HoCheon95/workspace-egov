package kr.or.ddit.hw05.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.function.Failable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import kr.or.ddit.hw05.domain.ConvertablePair;
import kr.or.ddit.hw05.dto.ExchangeRequest;
import kr.or.ddit.hw05.dto.ExchangeResponse;

public class ExchangeService {
    private double dynamicRate;
    @FunctionalInterface
    public interface Converter{
        double convert(double amount);
        static Converter identity(){
            return amount -> amount;
        }
    }

    private static double rate;

    public static double getRateFromNaver() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("https://finance.naver.com/marketindex/exchangeList.naver"))
                        .header("accept", "text/html")
                        .build();
        HttpResponse<String> resp;
        try {
            resp = httpClient.send(req, BodyHandlers.ofString());
            String respBody = resp.body();
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
            Document document = Jsoup.parse(respBody);
            return document.select(".tit")
                    .stream()
                    .filter(el->el.text().contains("USD"))
                    .findFirst()
                    // .map(el->el.nextElementSibling())
                    .map(el->el.parent())
                    .map(p->p.selectFirst(".sale"))
                    .map(s->s.text())
                    .map(Failable.asFunction(t->formatter.parse(t).doubleValue()))
                    .orElse(1500d);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static final Map<ConvertablePair, Converter> converterMap;
    static {
        rate = getRateFromNaver();
        Currency won = Currency.getInstance("KRW");
        Currency dlla = Currency.getInstance("USD");
        converterMap = new HashMap<>();
        converterMap.put(new ConvertablePair(won, dlla), amount -> amount / rate);
        converterMap.put(new ConvertablePair(dlla, won), amount -> amount * rate);
        converterMap.put(new ConvertablePair(won, won), Converter.identity());
        converterMap.put(new ConvertablePair(dlla, dlla), Converter.identity());
    }

    public List<Currency> getConvertableCurrencies(){
        return converterMap.keySet().stream()
                .flatMap(cp -> Stream.of(cp.getFrom(), cp.getTo()))
                .distinct()
                .toList();

    }

    public ExchangeResponse exchange(ExchangeRequest reqDto, Locale locale) {
        double amount = reqDto.getAmount();
        Currency from = reqDto.getFrom();
        Currency to = reqDto.getTo();
        ConvertablePair key = new ConvertablePair(from, to);
        Converter converter = converterMap.get(key);
        if(converter == null){
            throw new IllegalArgumentException("환전 불가능한 화폐임.");
        }

        double result = converter.convert(amount);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(to);

        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        String formatterdResult = formatter.format(result);
        return ExchangeResponse.builder()
            .amount(amount)
            .from(from)
            .to(to)
            .rate(rate)
            .result(result)
            .formattedResult(formatterdResult)
            .build();
    }
}
