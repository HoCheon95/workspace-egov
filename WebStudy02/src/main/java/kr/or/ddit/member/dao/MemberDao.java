package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.dto.MemberDto;

/**
 * 사용자 관리와 인증에 사용할 Persistence Layer
 */
public interface MemberDao {
    MemberDto selectMember(String username);
    List<MemberDto> selectMemberList();
    int updatePassword(String username, String password);
}
