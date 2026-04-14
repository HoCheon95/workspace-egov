package kr.or.ddit.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.member.dto.MemberDto;

/**
 * 사용자 관리와 인증에 사용할 Persistence Layer
 */
public interface MemberMapper {
    MemberDto selectMember(@Param("memId") String username);
    List<MemberDto> selectMemberList();
    int updatePassword(@Param("username") String username, @Param("password") String password);
}
