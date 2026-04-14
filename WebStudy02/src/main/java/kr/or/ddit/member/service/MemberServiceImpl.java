package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class MemberServiceImpl implements MemberService{

    private MemberMapper dao = new MapperProxyGenerator().generateMapperProxy(MemberMapper.class);
    private AuthenticateService authService = new AuthenticateService();

    @Override
    public List<MemberDto> readMemberList() {
        return dao.selectMemberList();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException {
        // 1. 인증
        authService.authenticate(username, oldPassword);
        // 2. 인증 성공했다면, 비번 수정
        dao.updatePassword(username, newPassword);
    }

}
