package kr.or.ddit.common.exception;

import lombok.Data;

@Data
public class OurBusinessException extends RuntimeException{
    private final Object domain;

    public OurBusinessException(Object domain) {
        super();
        this.domain = domain;
    }
}
