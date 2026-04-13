package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDto;

public class MemberMapperImplTest {
    MemberDao mapper = new MemberMapperImpl();
    @Test
    void testSelectMember() {
        MemberDto member =mapper.selectMember("a001");
        System.out.println(member);
        assertNotNull(member);
    }

    @Test
    void testSelectMemberList() {

    }

    @Test
    void testUpdatePassword() {

    }
}
