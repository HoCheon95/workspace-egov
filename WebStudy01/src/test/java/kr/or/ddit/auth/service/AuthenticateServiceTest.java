package kr.or.ddit.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDto;

public class AuthenticateServiceTest {
    AuthenticateService service = new AuthenticateService();

    @Test
    void testAuthenticate() {
        MemberDto member = service.authenticate("c001", "1234");
        assertNull(member);
    }
}
