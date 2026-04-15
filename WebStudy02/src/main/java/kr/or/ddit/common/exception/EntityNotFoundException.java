package kr.or.ddit.common.exception;

public class EntityNotFoundException extends RuntimeException{

    /**
     * 단건 조회의 경우 해당 레코드가 존재하지 않음을 표현함
     */
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
