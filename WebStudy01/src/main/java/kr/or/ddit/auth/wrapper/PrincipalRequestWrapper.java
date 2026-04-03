package kr.or.ddit.auth.wrapper;

import java.security.Principal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.member.dto.MemberDto;

public class PrincipalRequestWrapper extends HttpServletRequestWrapper{

    public PrincipalRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Principal getUserPrincipal() {
        MemberDto authMember = (MemberDto)getSession().getAttribute("authMember");

        if(authMember != null) {
            return new Authentication(authMember, authMember.getMemRoles());
        } else {
            return super.getUserPrincipal();
        }
    }

    @Override
    public boolean isUserInRole(String role) {
        Authentication authentication = (Authentication) getUserPrincipal();
        if(authentication != null){
            return authentication.getAuthorities().contains(role);
        } else {
            return super.isUserInRole(role);
        }
    }
}
