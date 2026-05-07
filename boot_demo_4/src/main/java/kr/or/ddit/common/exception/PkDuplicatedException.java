package kr.or.ddit.common.exception;

public class PkDuplicatedException extends OurBusinessException{

    public PkDuplicatedException(Object domain) {
        super(domain);
    }
}
