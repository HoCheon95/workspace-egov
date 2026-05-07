package kr.or.ddit.validate.groups;

import jakarta.validation.groups.Default;

/**
 * 도메인 등록을 표현하기 위한 marker interface
 * - marker interface: 구현체가 없는 인터페이스, 특정한 속성이나 의미를 표현하기 위해 사용
 * - validation group으로 활용하기 위해 정의
 * - 검증 어노테이션에서 groups 속성에 이 인터페이스를 지정
 * -> 검증 시 해당 그룹에 속한 검증 어노테이션만 적용
 * - 예시: @NotBlank(groups = InsertGroup.class)
 */
public interface InsertGroup extends Default {

}
