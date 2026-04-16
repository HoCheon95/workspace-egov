package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import kr.or.ddit.mybatis.SqlSessionContext;

public class MemberMapperImplTest {
    MemberMapper mapper = new MapperProxyGenerator().generateMapperProxy(MemberMapper.class);
    private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    @Test
    void testInsertMemver() {
    //     MemberDto dummy = MemberDto.builder()
    //             .memId("z009")
    //             .memPass("java")
    //             .memName("더미")
    //             .memZip("11111")
    //             .memAdd1("상위")
    //             .memAdd2("하위")
    //             .memMail("aa@gmail.com")
    //             .build();
    //     try(
    //         SqlSession sqlSession = sqlSessionFactory.openSession();
    //     ) {
    //         SqlSessionContext.setSqlSession(sqlSession);
    //         mapper.insertMember(dummy);
    //         mapper.insertMemberRole(dummy.getMemId());
    //     }finally {
    //         SqlSessionContext.clearSqlSession();
    //     }
    }

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
