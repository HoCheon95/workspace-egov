package kr.or.ddit.mvc.exception;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CommandObjectBindException extends RuntimeException{
    private Object commandObject;
    private Map<String, List<String>> errors;

    public CommandObjectBindException(Object commandObject, Map<String, List<String>> errors) {
        super("%s 커맨드 오브젝트 바인드 과정에서 검증을 통과하지 못함.".formatted(commandObject.getClass()));
        this.commandObject = commandObject;
        this.errors = errors;
    }
}
