package kr.or.ddit.member.service;

import java.util.List;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
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

    @Override
    public void registerMember(MemberDTO member) {
        if (mapper.selectMember(member.getMemId()) != null) {
            throw new PkDuplicatedException(member);
        }

        int cnt = mapper.insertMember(member);
        if (cnt > 0) {
            // 예외
            mapper.insertMemberRole(member.getMemId());
        }
    }

    @Override
    public void removeMember(String memId, String memPass) {
        // TODO Auto-generated method stub

    }

}
