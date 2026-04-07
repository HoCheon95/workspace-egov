package kr.or.ddit.auth.exception;

public class BadCredentialException extends AuthenticationException {

    public BadCredentialException() {
        super("비밀 번호 오류");
    }
}
