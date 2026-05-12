package kr.or.ddit.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

}
