package kr.or.ddit.member.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.ListUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDtoTest {
    static Validator validator;

    @BeforeAll
    static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidateUtils() {
        MemberDto target = MemberDto.builder()
                .memComtel("042-111-1111")
                .memHometel("asdfasdf")
                .memHp("000-000-0000")
                .build();
        Map<String, List<String>> errors = ValidateUtils.validate(target, InsertGroup.class);
        errors.forEach((k, v) -> log.info("{} : {}", k, v));
    }

    @Test
    void testDelte() {
        MemberDto target = MemberDto.builder()
                .memId("a")
                .build();
        Set<ConstraintViolation<MemberDto>> constraintViolation = validator.validate(target, DeleteGroup.class);

        Map<String, List<String>> errors = constraintViolation.stream()
                .collect(Collectors.toMap(
                        cv -> cv.getPropertyPath().toString(),
                        cv -> List.of(cv.getMessage()),
                        (l1, l2) -> ListUtils.union(l1, l2)));
        
        errors.forEach((k, v)-> log.info("{} : {}", k, v));
    }


    @Test
    void testUpdate() {
        MemberDto target = MemberDto.builder()
                .build();
        Set<ConstraintViolation<MemberDto>> constraintViolation = validator.validate(target, UpdateGroup.class);

        Map<String, List<String>> errors = constraintViolation.stream()
                .collect(Collectors.toMap(
                        cv -> cv.getPropertyPath().toString(),
                        cv -> List.of(cv.getMessage()),
                        (l1, l2) -> ListUtils.union(l1, l2)));
        
        errors.forEach((k, v)-> log.info("{} : {}", k, v));
    }
    
    @Test
    void testInsert() {
        MemberDto target = MemberDto.builder()
                .build();
        Set<ConstraintViolation<MemberDto>> constraintViolation = validator.validate(target, InsertGroup.class);

        Map<String, List<String>> errors = constraintViolation.stream()
                .collect(Collectors.toMap(
                        cv -> cv.getPropertyPath().toString(),
                        cv -> List.of(cv.getMessage()),
                        (l1, l2) -> ListUtils.union(l1, l2)));
        
        errors.forEach((k, v)-> log.info("{} : {}", k, v));
    }

    @Test
    void test1() {
        MemberDto target = MemberDto.builder()
                .memId("a1234")
                .memPass("abd")
                .memMail("aa@naver.com")
                .memBir(LocalDate.now())
                .memMileage(0)
                .build();
        Set<ConstraintViolation<MemberDto>> constraintViolation = validator.validate(target);
        constraintViolation.forEach(cv -> {
            String property = cv.getPropertyPath().toString();
            String message = cv.getMessage();
            log.info("{} : {}", property, message);
        });

        Map<String, List<String>> errors = constraintViolation.stream()
                .collect(Collectors.toMap(
                        cv -> cv.getPropertyPath().toString(),
                        cv -> List.of(cv.getMessage()),
                        (l1, l2) -> ListUtils.union(l1, l2)));
        log.info("{}", errors);
    }

    
}
