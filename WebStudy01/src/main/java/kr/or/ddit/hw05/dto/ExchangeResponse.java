package kr.or.ddit.hw05.dto;

import java.io.Serializable;
import java.util.Currency;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Builder
public class ExchangeResponse implements Serializable{
    private double amount;
    private Currency from;
    private Currency to;

    private double rate;
    private double result;
    private String formattedResult;
    
}
