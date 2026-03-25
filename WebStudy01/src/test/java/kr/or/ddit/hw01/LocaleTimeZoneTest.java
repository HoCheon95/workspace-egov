package kr.or.ddit.hw01;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LocaleTimeZoneTest {

    @Test
    void test4(){
        // 1. 타임존 데이터 수집 및 표시용 이름 생성
        // 시스템에서 사용 가능한 모든 타임존 ID들을 가져옴
        Set<String>zoneSet = ZoneId.getAvailableZoneIds();
        // 타임존 ID를 키로, 표시용 이름을 값으로 하는 맵 생성
        Map<String, String> zoneMap = new HashMap<>();
        // 각 타임존 ID에 대해 표시용 이름 생성
        for(String singleZone : zoneSet){
            // 타임존 ID로부터 ZoneId 객체 생성
            ZoneId tz = ZoneId.of(singleZone);
            // 프랑스어 캐나다 로케일로 타임존의 전체 표시 이름 가져오기
            String name = tz.getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH);
            // 맵에 타임존 ID와 표시용 이름 저장
            zoneMap.put(singleZone, name);
        }
    }

    @Test
    void test3(){
        // 2. 로케일 데이터 수집 및 표시용 이름 생성
        // 시스템에서 사용 가능한 모든 로케일 객체들을 가져옴
        Locale[] locales = Locale.getAvailableLocales();
        // 로케일의 언어 태그를 키로, 표시용 이름을 값으로 하는 맵 생성
        Map<String, String> localeMap = new HashMap<>();
        // 각 로케일에 대해 표시용 이름 생성
        for (Locale locale : locales) {
            // 로케일로부터 BCP 47 언어 태그 생성 (예: ko-KR, en-US)
            String languageTag = locale.toLanguageTag();
            // 해당 로케일로 표시한 로케일 이름 가져오기 (자체 표시)
            String name = locale.getDisplayName(locale);
            // 이름이 빈 문자열이면 건너뜀 (유효하지 않은 로케일 필터링)
            if(name.isBlank()) continue;
            // 맵에 언어 태그와 표시용 이름 저장
            localeMap.put(languageTag, name);
        }
    }

    // @Test
    void test2() {
        System.out.println("test case2");
    }

    // @Test
    void test1() {
        System.out.println("test case1");
    }
}