package kr.or.ddit.auth.service;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoInMemoryImpl;
import kr.or.ddit.member.dto.MemberDto;

/**
 * 사용자의 신원 확인(인증)을 담당할 business logic layer
 */
public class AuthenticateService {

    private MemberDao dao = new MemberDaoInMemoryImpl();

    public MemberDto authenticate(String username, String password){
        // 1. Dao에서 사용자 조회
        MemberDto member = dao.selectMember(username);

        // 2. 사용자 존재 여부 + 비밀번호 비교
        if(member == null) {
            return null;
        }
        if(password.equals(member.getMemPass())){
            return member;
        } else {
            return null;
        }
    }
}
