package kr.or.ddit.hw05.validate;

import java.util.Currency;

import kr.or.ddit.hw05.dto.ExchangeRequest;

public class ExchangeValidator {
    /**
     * 
     * @param params
     * @return
     * @throws RuntimeException
     */
    public ExchangeRequest validate(String... params) throws RuntimeException {

        // 1. 파라미터 개수 확인
        if (params == null || params.length < 3) {
            throw new IllegalArgumentException("환전에는 3개의 파라미터가 필요함.");
        }

        // 2. 숫자 값 검증
        double amount = -1;
        try{
            amount = Double.parseDouble(params[0]);
        } catch (RuntimeException e){ //  예외전환처리
            throw new IllegalArgumentException("amount 금액은 숫자로만..");
        }

        // 3. 통화코드 검증
        try{
            Currency from = Currency.getInstance(params[1]);
            Currency to = Currency.getInstance(params[2]);
            
            return new ExchangeRequest(amount, from, to);
        } catch (RuntimeException e){
            throw new IllegalArgumentException("지원하지 않는 화폐단위임.");
        }
    }
}
