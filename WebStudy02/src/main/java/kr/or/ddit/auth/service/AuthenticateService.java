package kr.or.ddit.auth.service;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.exception.BadCredentialException;
import kr.or.ddit.auth.exception.UsernameNotFoundException;
import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyGenerator;

/**
 * 사용자의 신원 확인(인증)을 담당할 business logic layer
 */
public class AuthenticateService {

    private MemberMapper dao = new MapperProxyGenerator().generateMapperProxy(MemberMapper.class);

    /**
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException : 인증 실패
     */
    public MemberDto authenticate(String username, String password) throws AuthenticationException{
        // 1. Dao에서 사용자 조회
        MemberDto member = dao.selectMember(username);

        // 2. 사용자 존재 여부 + 비밀번호 비교
        if(member == null) {
            throw new UsernameNotFoundException(username);
        }
        if(password.equals(member.getMemPass())){
            return member;
        } else {
            throw new BadCredentialException();
        }
    }
}
