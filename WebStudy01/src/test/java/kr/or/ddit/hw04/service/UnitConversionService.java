package kr.or.ddit.hw04.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import kr.or.ddit.hw04.domain.Unit;
import kr.or.ddit.hw04.dto.ConversionRequest;
import kr.or.ddit.hw04.dto.ConversionResponse;

class UnitConversionServiceTest {

    private UnitConversionServiceTest service;

    @BeforeEach
    void setUp() {
        // 테스트 수행 전 서비스 객체를 초기화한다. 🔴
        service = new UnitConversionServiceTest();
    }

    @Test
    @DisplayName("KM -> MILE 정방향 변환 테스트 (10km)")
    void testKmToMile() {
        // Given: 10 KM를 MILE로 변환하는 요청을 생성한다. 🔴
        ConversionRequest req = new ConversionRequest(10.0, Unit.KM, Unit.MILE);
        
        // When: 서비스를 호출하여 변환을 수행한다. 🔴
        ConversionResponse res = service.convert(req, Locale.US);

        // Then: 결과값이 약 6.2137인지 확인해야 한다. 🔴
        assertEquals(6.2137, res.getResult(), 0.0001);
        assertEquals("KM", res.getFrom());
        assertEquals("MILE", res.getTo());
    }

    private ConversionResponse convert(ConversionRequest req, Locale us) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convert'");
    }

    @Test
    @DisplayName("MILE -> KM 역방향 변환 테스트")
    void testMileToKm() {
        // Given: 6.21371 MILE을 KM로 변환하는 요청을 생성한다. 🔴
        ConversionRequest req = new ConversionRequest(6.21371, Unit.MILE, Unit.KM);
        
        // When: 변환을 수행한다. 🔴
        ConversionResponse res = service.convert(req, Locale.US);

        // Then: 결과값이 10에 수렴하는지 확인해야 한다. 🔴
        assertEquals(10.0, res.getResult(), 0.001);
    }

    @Test
    @DisplayName("경계값 테스트: 0 입력 (0 KM -> 0 MILE)")
    void testZeroValue() {
        // Given: 입력값이 0인 경우를 상정한다. 🔴
        ConversionRequest req = new ConversionRequest(0.0, Unit.KM, Unit.MILE);
        
        // When: 변환 결과가 0이어야 한다. 🔴
        ConversionResponse res = service.convert(req, Locale.US);

        // Then: 0.0이 정확히 반환되는지 확인해야 한다. 🔴
        assertEquals(0.0, res.getResult());
    }

    @Test
    @DisplayName("경계값 테스트: 음수 입력 (-10 C -> F)")
    void testNegativeTemperature() {
        // Given: -10 섭씨 온도를 화씨로 변환한다. (-10 * 9/5 + 32 = 14) 🔴
        ConversionRequest req = new ConversionRequest(-10.0, Unit.C, Unit.F);
        
        // When: 서비스를 호출한다. 🔴
        ConversionResponse res = service.convert(req, Locale.US);

        // Then: 결과값이 14.0인지 확인해야 한다. 🔴
        assertEquals(14.0, res.getResult());
    }

    @Test
    @DisplayName("경계값 테스트: 매우 큰 수 변환 (999,999,999 KM)")
    void testLargeValue() {
        // Given: 매우 큰 숫자 데이터를 준비한다. 🔴
        double largeVal = 999999999.0;
        ConversionRequest req = new ConversionRequest(largeVal, Unit.KM, Unit.MILE);
        
        // When: 변환을 수행하고 포맷팅된 문자열을 확인한다. 🔴
        ConversionResponse res = service.convert(req, Locale.US);

        // Then: double 정밀도 내에서 정상 계산되었는지 확인해야 한다. 🔴
        assertTrue(res.getResult() > 0);
        // Locale.US 기준 콤마(,)가 포함된 포맷팅 결과인지 확인한다. 🔴
        assertTrue(res.getFormattedResult().contains(","));
    }

    @Test
    @DisplayName("경계값 테스트: 매우 작은 소수 (0.0001 M -> FT)")
    void testSmallDecimal() {
        // Given: 0.0001 미터를 피트로 변환한다. (0.0001 * 3.28084) 🔴
        double smallVal = 0.0001;
        ConversionRequest req = new ConversionRequest(smallVal, Unit.M, Unit.FT);
        
        // When: 변환을 수행한다. 🔴
        ConversionResponse res = service.convert(req, Locale.US);

        // Then: 소수점 아래 결과가 정밀하게 계산되어야 한다. 🔴
        assertEquals(0.000328, res.getResult(), 0.000001);
    }
}