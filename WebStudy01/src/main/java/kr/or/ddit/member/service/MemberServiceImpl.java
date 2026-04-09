package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.dto.MemberDto;

public class MemberServiceImpl implements MemberService{

    private MemberDao dao = new MemberDaoImpl();

    @Override
    public List<MemberDto> readMemberList() {
        return dao.selectMemberList();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException {
        
    }

}
