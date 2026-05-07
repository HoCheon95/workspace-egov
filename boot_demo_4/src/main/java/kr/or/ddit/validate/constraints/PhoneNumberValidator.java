package kr.or.ddit.validate.constraints;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private PhoneNumber annotation;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value))
            return true;
        return value.matches(annotation.regex());
    }
}
