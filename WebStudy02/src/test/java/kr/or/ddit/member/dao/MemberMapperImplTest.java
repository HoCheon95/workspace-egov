package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class MemberMapperImplTest {
    MemberMapper mapper = new MapperProxyGenerator().generateMapperProxy(MemberMapper.class);
    @Test
    void testSelectMember() {
        MemberDto member =mapper.selectMember("a001");
        System.out.println(member);
        assertNotNull(member);
    }

    @Test
    void testSelectMemberList() {
        mapper.selectMemberList().forEach(System.out::println);
    }

    @Test
    void testUpdatePassword() {
        int cnt = mapper.updatePassword("a001", "1111");
        assertEquals(1, cnt);
        System.out.println(mapper.selectMember("a001").getMemPass());
    }
}
