package kr.or.ddit.reflection;

import kr.or.ddit.member.dto.MemberDto;

public class DummyFramWork {
    public static Object getObject() {
        return MemberDto.builder()
                .memId("a001")
                .memPass("java")
                .memName("더미")
                .build();
    }
}
