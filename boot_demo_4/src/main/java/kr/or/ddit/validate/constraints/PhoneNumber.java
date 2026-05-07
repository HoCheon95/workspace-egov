package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {

    String regex() default "\\d{2,3}-\\d{3,4}-\\d{4}";

    String displayPattern() default "042-123-4567";

    String message() default "전화번호 형식 확인 {displayPattern}, 입력값: ${validatedValue} ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
