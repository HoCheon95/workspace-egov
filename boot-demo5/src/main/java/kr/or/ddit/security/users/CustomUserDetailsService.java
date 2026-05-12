package kr.or.ddit.security.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberDTO realUser = mapper.selectMember(username);

        if(realUser == null)
            throw new UsernameNotFoundException("%s 사용자 없음.".formatted(username));

        // 그룹 역할이나 그룹내 인가 정보를 추가로 로딩
        MemberDtoWrapper wrapper = new MemberDtoWrapper(realUser);
        // wrapper.getAuthorities().addAll(그룹역할)

        return new MemberDtoWrapper(realUser);
    }

}
