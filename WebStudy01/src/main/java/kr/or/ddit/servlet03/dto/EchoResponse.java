package kr.or.ddit.servlet03.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data // --> 빌더 패턴 적용
@Builder
public class EchoResponse implements Serializable{
    private String original;    // 원본 메시지
    private String echoed;      // 가공된 메시지
    private int length;         // 메시지 길이
    private String receivedAt;  // 수신 시각

    // Gson.toJson()이 이 객체를 JSON으로 변환한다
    // → {"original":"안녕하세요","echoed":"...","length":5,"receivedAt":"..."}

    // 생성자 또는 setter
}
