package kr.or.ddit.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import kr.or.ddit.mybatis.SqlSessionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberServiceImpl implements MemberService{
    // private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    private MemberMapper mapper = new MapperProxyGenerator().generateMapperProxy(MemberMapper.class);
    private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    private AuthenticateService authService = new AuthenticateService();

    @Override
    public List<MemberDto> readMemberList() {
        return mapper.selectMemberList();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException {
        // 1. 인증
        authService.authenticate(username, oldPassword);
        // try (
        //     SqlSession sqlSession = sqlSessionFactory.openSession();
        // ) {
        //     SqlSessionContext.setSqlSession(sqlSession);
            // 2. 인증 성공했다면, 비번 수정
            mapper.updatePassword(username, newPassword);

        //     sqlSession.commit();
        // } finally {
        //     SqlSessionContext.clearSqlSession();
        // }
    }

    @Override
    public void registMember(MemberDto member) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyMember'");
    }

    @Override
    public MemberDto readMember(String memId) throws EntityNotFoundException {
        // 데이터 베이스에 접근할 매퍼
        MemberDto member = mapper.selectMember(memId);
        if(log.isTraceEnabled())
            log.trace("trace 등급으로 기록한 메시지 {}",member);
        if(log.isInfoEnabled())
            log.info("debug 등급으로 기록한 메시지 {}", member);
        if (member == null) {
            throw new EntityNotFoundException("%s 사용자 없음.".formatted(member));
        }
        return member;
    }

    @Override
    public void modifyMember(MemberDto member) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyMember'");
    }

    @Override
    public void removeMember(MemberDto authToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }

}
