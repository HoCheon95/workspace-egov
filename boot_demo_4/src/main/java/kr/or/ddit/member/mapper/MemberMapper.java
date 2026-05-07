package kr.or.ddit.member.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.MemberDTO;

/**
 * 사용자 관리(CRUD)와 인증에 사용할 Persistence Layer
 */

@Mapper
public interface MemberMapper {
    /**
     * 사용자 아이디를 이용하여 사용자 정보를 조회하는 메서드
     * 
     * @param username 조회할 사용자 아이디
     * @return 조회된 사용자 정보가 담긴 MemberDTO 객체, 조회 대상이 없으면 null 반환
     */
    MemberDTO selectMember(@Param("memId") String username);

    /**
     * (관리자용) 모든 사용자 정보를 조회하는 메서드
     * 
     * @return 사용자 정보가 담긴 MemberDTO 객체의 리스트
     */
    List<MemberDTO> selectMemberList(PaginationInfo<?> paginationInfo);

    /**
     * 사용자 비밀번호를 업데이트하는 메서드
     * 
     * @param username 사용자 아이디
     * @param password 새로운 비밀번호
     * @return 업데이트된 레코드 수(성공 시 1, 실패 시 0)
     */
    int updatePassword(@Param("username") String username, @Param("password") String password);

    /**
     * 신규 회원 등록
     * 
     * @param memberDTO 회원 정보가 담긴 DTO 객체
     * @return 삽입된 레코드 수(성공 시 1, 실패 시 0)
     */
    int insertMember(MemberDTO memberDTO);

    /**
     * 신규 회원 역할 등록 (ROLE_USER)
     * 
     * @param memId 회원 아이디
     * @return 삽입된 레코드 수(성공 시 1, 실패 시 0)
     */
    int insertMemberRole(@Param("memId") String memId);

    /**
     * 회원 정보 업데이트
     * 
     * @param memberDTO 회원 정보가 담긴 DTO 객체
     * @return 업데이트된 레코드 수(성공 시 1, 실패 시 0)
     */
    int updateMember(MemberDTO memberDTO);

    /**
     * 회원 삭제
     * 
     * @param memId 삭제할 회원의 아이디
     * @return 삭제된 레코드 수(성공 시 1, 실패 시 0)
     */
    int deleteMember(@Param("memId") String memId);

    /**
     * 회원 역할 삭제
     * 
     * @param memId 삭제할 회원의 아이디
     * @return 삭제된 레코드 수(성공 시 1, 실패 시 0)
     */
    int deleteMemberRole(@Param("memId") String memId);

    /**
     * 페이징을 위한 전체 레코드 수 조회
     * @return
     */
    int selectTotalRecord(PaginationInfo<?> paginationInfo);

}
