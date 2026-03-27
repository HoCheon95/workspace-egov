package kr.or.ddit.hw05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Currency;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class CurrencyTest {

	@Test
	void test() {
		Currency won = Currency.getInstance(Locale.KOREA);
		System.out.println(won.getCurrencyCode());
		System.out.println(won.getDisplayName());
		System.out.println(won.getSymbol());
		Currency dollar = Currency.getInstance(Locale.US);
		System.out.println(dollar.getCurrencyCode());
		System.out.println(dollar.getDisplayName());
		System.out.println(dollar.getSymbol());
	}

}
