package kr.or.ddit.hw05.service;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import kr.or.ddit.hw05.domain.ConvertablePair;
import kr.or.ddit.hw05.dto.ExchangeRequest;
import kr.or.ddit.hw05.dto.ExchangeResponse;
import kr.or.ddit.hw06.ProxyHeaderServlet.ExchangeRateParser;

public class ExchangeService {
    private double dynamicRate;
    @FunctionalInterface
    public interface Converter{
        double convert(double amount, double rate);
        static Converter identity(){
            return (amount, rate) -> amount;
        }
    }

    // private static final double rate = 1500;
    public static final Map<ConvertablePair, Converter> converterMap;
    static {
        Currency won = Currency.getInstance("KRW");
        Currency dlla = Currency.getInstance("USD");
        converterMap = new HashMap<>();
        converterMap.put(new ConvertablePair(won, dlla), (amount, rate) -> amount / rate);
        converterMap.put(new ConvertablePair(dlla, won), (amount, rate) -> amount * rate);
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
        String targetUrl = "https://finance.naver.com/marketindex/exchangeList.naver";
        this.dynamicRate = ExchangeRateParser.getRealTimeRate(targetUrl);

        double amount = reqDto.getAmount();
        Currency from = reqDto.getFrom();
        Currency to = reqDto.getTo();
        ConvertablePair key = new ConvertablePair(from, to);
        Converter converter = converterMap.get(key);
        if(converter == null){
            throw new IllegalArgumentException("환전 불가능한 화폐임.");
        }

        double result = converter.convert(amount, this.dynamicRate);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(to);

        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        String formatterdResult = formatter.format(result);
        return ExchangeResponse.builder()
            .amount(amount)
            .from(from)
            .to(to)
            .rate(this.dynamicRate)
            .result(result)
            .formattedResult(formatterdResult)
            .build();
    }
}
