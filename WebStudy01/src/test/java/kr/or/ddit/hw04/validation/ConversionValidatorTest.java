package kr.or.ddit.hw04.validation;

import org.junit.jupiter.api.Test;

import kr.or.ddit.hw04.dto.ConversionRequest;
import kr.or.ddit.hw04.exception.UnitConversionException;

public class ConversionValidatorTest {

    @Test
    void testValidateInvalid(){
        try{
            ConversionValidator.validate("1234", "KM", "MILE");
        } catch (UnitConversionException e) {
            // 400 에러 발생
            System.out.println("400 에러, " + e.getMessage());;
        }
    }

    @Test
    void testValidateValid(){
        try{
            ConversionRequest reqDto = ConversionValidator.validate("1234", "KM", "MILE");
            System.out.println(reqDto);
        } catch (UnitConversionException e) {
            // 400 에러 발생
            System.out.println("400 에러, " + e.getMessage());;
        }
    }

}