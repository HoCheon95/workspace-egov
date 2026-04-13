package kr.or.ddit.auth.exception;

/**
 * Throwable : 모든 except 과 error 의 최상위 표현
 *  Error : 개발자가 직접 처리할 수 없는 시스템 디폴트 상황
 *  Exception : 필요하다면 처리 가능한 예외적 상황
 *      Checked exception : 발생 가능한 코드가 있으면, 반드시 처리를 해야만 컴파일 에러를 피할 수 있는 예외
 *          ex) IOException, SQLException
 *      Unchecked exception : 처리하지 않더라도 컴파일 에러가 발생하지 않고, 기본적으로 예외 회피 전략으로 처리되는 예외
 *          ex) NullPointerException, IllegalArgumentException
 *  예외 처리 방법
 *  1. 예외 회피 : throws 로 메소드 호출자에게 예외를 떠넘기는 방식
 *  2. 예외 전환 : try~catch~finally - catch 에서 예외의 종류를 변환하여 던지는 구조
 *                 -> checked 예외를 uncheced 예외로 전환하거나, 발생한 예외를 좀 더 구체적인 커스텀 예외를 전환할때
 * 
 *  커스텀 예외
 *  1. 예외의 종류 결정
 *      checked exception : extends Exception
 *      unchecked exception : extends RuntimeException
 *  2. 상황에 맞는 커스텀 예외를 생성하고 throw
 * 
 *  인증 실패를 포괄적으로 표현할 예외
 *      UsernameNotFoundException, BadCredentialException 의 상위클래스
 */
public class AuthenticationException extends RuntimeException{

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
