package kr.or.ddit.security.users;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.dto.MemberDTO;
import lombok.ToString;

@ToString(callSuper = true)
public class MemberDtoWrapper extends User{
    private final MemberDTO realUser;

    public MemberDtoWrapper(MemberDTO realUser) {
        super(
            realUser.getMemId(),
            realUser.getMemPass(),
            !realUser.isMemDelete(),
            !realUser.isMemDelete(),
            !realUser.isMemDelete(),
            !realUser.isMemDelete(),
            AuthorityUtils.createAuthorityList(realUser.getMemRoles())
        );
        this.realUser = realUser;
    }

    public MemberDTO getRealUser() {
        return realUser;
    }


}
