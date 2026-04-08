package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDto;

public class MemberDaoImplTest {

    MemberDaoImpl dao = new MemberDaoImpl();

    @Test
    void testSelectDummy() {
        String username = "a001";
        String password = "asd' or '1' ='1'; delete from member where '1'='1'";
        MemberDto member =dao.selectMemberDummy(username, password);
        System.out.println(member);
    }

    @Test
    void testSqlInjection() {
        MemberDto member = dao.selectMember("fadew' or '1'='1");
        assertNull(member);
    }

    @Test
    void testSelectMember() {
        MemberDto member = dao.selectMember("a001");
        assertNotNull(member);
        member = dao.selectMember("fadew");
        assertNotNull(member);
    }
}
