package kr.or.ddit.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.member.dto.MemberDto;

/**
 * 사용자 관리(CRUD)와 인증에 사용할 Persistence Layer
 */
public interface MemberMapper {
    /**
     * 회원 상제 조회
     * @param username
     * @return
     */
    MemberDto selectMember(@Param("memId") String username);

    /**
     * 회원 목록 조회
     * @return
     */
    List<MemberDto> selectMemberList();

    /**
     * 비밀 번호 변경
     * @param username
     * @param password
     * @return
     */
    int updatePassword(@Param("username") String username, @Param("password") String password);

    /**
     * 신규 회원 등록
     * @param member
     * @return
     */
    int insertMember(MemberDto member);

    /**
     * 신규 회원의 역할을 ROLE_USER로 등록
     * @return
     */
    int insertMemberRole(String memId);

    /**
     * 회원 정보 수정
     * @param member
     * @return
     */
    int updateMember(MemberDto member);
    
    /**
     * 회원 정보 삭제
     * @param memId
     * @return
     */
    int deleteMember(String memId);

    /**
     * 탈퇴 회원의 역할 제거
     * @param memId
     * @return
     */
    int deleteMemberRole(String memId);
}
