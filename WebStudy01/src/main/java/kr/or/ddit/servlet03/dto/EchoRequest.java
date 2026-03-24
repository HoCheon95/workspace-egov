package kr.or.ddit.servlet03.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// @AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EchoRequest implements Serializable {
    private final String message;
    private final String language;      // "ko" 또는 "en"
    private String dummy;

    // Gson은 필드명과 JSON 키를 매칭한다
    // {"message":"안녕하세요","language":"ko"}
    // → message="안녕하세요", language="ko"

    // getter
}
