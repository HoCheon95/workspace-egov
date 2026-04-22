package kr.or.ddit.auth.exception;

public class UsernameNotFoundException extends AuthenticationException {
    public UsernameNotFoundException(String username) {
        super("%s 아이디로 등록된 사용자 없음.".formatted(username));
    }
    
}
