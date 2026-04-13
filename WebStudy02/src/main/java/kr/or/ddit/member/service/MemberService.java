package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.member.dto.MemberDto;

public interface MemberService {

    public List<MemberDto> readMemberList();
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException;
}
