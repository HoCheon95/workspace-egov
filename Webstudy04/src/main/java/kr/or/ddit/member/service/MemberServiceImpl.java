package kr.or.ddit.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.di.stereotype.Service;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.mybatis.SqlSessionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;
    private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    @Autowired
    private AuthenticateService authService;

    @Override
    public List<MemberDto> readMemberList() {
        return mapper.selectMemberList();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException {
        authService.authenticate(username, oldPassword);
        mapper.updatePassword(username, newPassword);
    }

    @Override
    public void registMember(MemberDto member) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SqlSessionContext.setSqlSession(sqlSession);

            log.info("회원 등록 시작: {}", member.getMemId());

            int cnt = mapper.insertMember(member);
            log.info("insertMember 결과 cnt = {}", cnt);

            if (cnt > 0) {
                log.info("insertMemberRole 실행 전: {}", member.getMemId());

                mapper.insertMemberRole(member.getMemId());

                log.info("insertMemberRole 실행 완료");

                sqlSession.commit();

                log.info("회원 등록 commit 완료");
            }

        } catch (Exception e) {
            log.error("회원 등록 중 예외 발생", e);
            throw new RuntimeException(e);

        } finally {
            SqlSessionContext.clearSqlSession();
        }
    }

    @Override
    public MemberDto readMember(String memId) throws EntityNotFoundException {
        // 데이터 베이스에 접근할 매퍼
        MemberDto member = mapper.selectMember(memId);
        if (log.isTraceEnabled())
            log.trace("trace 등급으로 기록한 메시지 {}", member);
        if (log.isInfoEnabled())
            log.info("debug 등급으로 기록한 메시지 {}", member);
        if (member == null) {
            throw new EntityNotFoundException("%s 사용자 없음.".formatted(member));
        }
        return member;
    }

    @Override
    public void modifyMember(MemberDto member) {
        authService.authenticate(member.getMemId(), member.getMemPass());
        mapper.updateMember(member);
    }

    @Override
    public void removeMember(MemberDto authToken) {
        authService.authenticate(authToken.getMemId(), authToken.getMemPass());
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession();) {
            SqlSessionContext.setSqlSession(sqlSession);
            mapper.deleteMemberRole(authToken.getMemId());
            mapper.deleteMember(authToken.getMemId());

            sqlSession.commit();
        } finally {
            SqlSessionContext.clearSqlSession();
        }
    }

}
