package kr.or.ddit.hw05.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Currency;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import kr.or.ddit.hw05.dto.ExchangeRequest;
import kr.or.ddit.hw05.dto.ExchangeResponse;

class ExchangeServiceTest {

	ExchangeService service = new ExchangeService();

	@Test
	void testExchange() {
		ExchangeRequest reqDto = new ExchangeRequest(1, Currency.getInstance("USD"), Currency.getInstance("KRW"));
		ExchangeResponse respDto = service.exchange(reqDto, Locale.US);
		assertEquals(1500d, respDto.getResult());
		System.out.println(respDto);
		reqDto = new ExchangeRequest(1500, Currency.getInstance("KRW"), Currency.getInstance("USD"));
		respDto = service.exchange(reqDto, Locale.US);
		assertEquals(1d, respDto.getResult());
		System.out.println(respDto);
	}

}
