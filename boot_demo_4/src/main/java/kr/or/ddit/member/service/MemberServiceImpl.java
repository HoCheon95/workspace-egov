package kr.or.ddit.member.service;

import java.util.List;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.common.exception.PkDuplicatedException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    private final MemberMapper mapper;

    @Override
    public void changePassword(String username, String oldPassword, String newPassword)
            throws AuthenticationException {
        // TODO Auto-generated method stub

    }

    @Override
    public void modifyMember(MemberDTO memberDTO) throws AuthenticationException {
        // TODO Auto-generated method stub

    }

    /**
     * 회원 상세 조회
     * 
     * @param memId 조회할 회원의 아이디
     * @return 조회된 회원 정보가 담긴 MemberDTO 객체
     * @throws EntityNotFoundException 조회 대상이 존재하지 않을 때 발생하는 예외
     */
    @Override
    public MemberDTO readMember(String memId) throws EntityNotFoundException {
        // 데이터 베이스에 접근할 매퍼
        MemberDTO member = mapper.selectMember(memId);
        if (log.isTraceEnabled())
            log.trace("trace 등급으로 기록한 메시지 {}", member);
        if (log.isInfoEnabled())
            log.info("debug 등급으로 기록한 메시지 {}", member);
        if (member == null) {
            throw new EntityNotFoundException("%s 사용자 없음.".formatted(member));
        }
        return member;
    }

    /**
     * (관리자용) 모든 사용자 정보를 조회하는 메서드
     * 
     * @return 사용자 정보 리스트
     */
    @Override
    public List<MemberDTO> readMemberList(PaginationInfo<?> paginationInfo) {
        int totalRecord = mapper.selectTotalRecord(paginationInfo);
        paginationInfo.setTotalRecord(totalRecord);
        return mapper.selectMemberList(paginationInfo);
    }

    @Override
    public boolean checkIdAvailable(String memId) {
        return mapper.selectMember(memId) == null;
    }

    /**
     * 회원 등록
     * 
     * @param memberDTO 등록할 회원 정보가 담긴 MemberDTO 객체
     */
    @Transactional // 트랜잭션 관리
    @Override
    public void registerMember(MemberDTO member) {
        if (mapper.selectMember(member.getMemId()) != null) {
            throw new PkDuplicatedException(member);
        }

        int cnt = mapper.insertMember(member);
        // if (cnt > 0) {
        //     // 예외
        //     throw new RuntimeException("트랜잭션 관리 여부를 확인하기 위한 강제 예외");
        // }
        mapper.insertMemberRole(member.getMemId());
    }

    @Override
    public void removeMember(String memId, String memPass) {
        // TODO Auto-generated method stub

    }

}
