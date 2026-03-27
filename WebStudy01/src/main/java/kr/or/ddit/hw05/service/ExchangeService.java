package kr.or.ddit.hw05.service;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import kr.or.ddit.hw05.domain.ConvertablePair;
import kr.or.ddit.hw05.dto.ExchangeRequest;
import kr.or.ddit.hw05.dto.ExchangeResponse;

public class ExchangeService {
    @FunctionalInterface
    public interface Converter{
        double convert(double amount);
        static Converter identity(){
            return amount -> amount;
        }
    }

    private static final double rate = 1500;
    public static final Map<ConvertablePair, Converter> converterMap;
    static {
        Currency won = Currency.getInstance("KRW");
        Currency dlla = Currency.getInstance("USD");
        converterMap = new HashMap<>();
        converterMap.put(new ConvertablePair(won, dlla), amount -> amount / rate);
        converterMap.put(new ConvertablePair(dlla, won), amount -> amount * rate);
        converterMap.put(new ConvertablePair(won, won), Converter.identity());
        converterMap.put(new ConvertablePair(dlla, dlla), Converter.identity());
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
