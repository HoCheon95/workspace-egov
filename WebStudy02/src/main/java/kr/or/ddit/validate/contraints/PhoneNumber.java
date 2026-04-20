package kr.or.ddit.validate.contraints;

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
    String displayPtrn() default "042-000-0000";

    String message() default "입력값(${validatedValue})전화번호 형식 확인 ({displayPtrn})";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
