package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.member.dto.MemberDto;

/**
 * 사용자 관리(CRUD)를 위한 Business Logic Layer
 */
public interface MemberService {

    /**
     * 회원 등록
     * @param member
     */
    void registMember(MemberDto member);

    /**
     * 회원 목록 조회 
     * @return
     */
    List<MemberDto> readMemberList();

    /**
     * 회원 상세 조회
     * @param memId
     * @return
     * @throws EntityNotFoundException 해당 회원이 없는 경우 발생함
     */
    MemberDto readMember(String memId) throws EntityNotFoundException;

    /**
     * 비밀번호 변경
     * @param username
     * @param oldPassword
     * @param newPassword
     * @throws AuthenticationException
     */
    void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException;

    /**
     * 회원 정보 수정(인증 필요)
     * @param member
     */
    void modifyMember(MemberDto member);

    /**
     * 회원 탈퇴(인증 필요)
     * @param authToken
     */
    void removeMember(MemberDto authToken);
}
