package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MemberServiceImplTest {
    MemberService service = new MemberServiceImpl();

    @Test
    void testChangePassword() {
        service.readMemberList().forEach(System.out::println);
    }

    @Test
    void testReadMemberList() {
        assertDoesNotThrow(() -> service.changePassword("a001", "1111", "java"));
    }
}
