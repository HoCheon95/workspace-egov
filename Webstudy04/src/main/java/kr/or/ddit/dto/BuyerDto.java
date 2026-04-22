package kr.or.ddit.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.or.ddit.validate.contraints.PhoneNumber;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 제조사 관리 domain layer
 * 
 * 1. 테이블(엔터티) 관계 파악, 메인 엔터티(1) 중심으로 관계 형성
 * BUYER(1)       LPROD(1) - 1:1
 * 2. 엔터티와 1:1 관계로 DTO 정의 -> 엔터티의 관계를 DTO 에 Has 관계로 반영
 * BuyerDto  Has  LprodDto
 * 3. 조인 쿼리 작성
 * 4. 쿼리 결과를 바인드하기 위한 resultType 대신 resultMap 으로 수동 바인드
 *      1) association (has a) : strict alias 로 대체 가능
 *      2) collection (has many) : many result 를 중복 제거하기 위한 id 필수
 * 
 * 1. 테이블(엔터티) 관계 파악, 메인 엔터티(1) 중심으로 관계 형성
 * BUYER(1)            PROD(N)
 * 2. 엔터티와 1:1 관계로 DTO 정의 -> 엔터티의 관계를 DTO 에 Has 관계로 반영
 * BuyerDto  Has Many  ProdDto
 */
@Data
@EqualsAndHashCode(of = "buyerId")
public class BuyerDto implements Serializable{
    @NotBlank(groups = UpdateGroup.class)
    private String buyerId;
    @NotBlank
    private String buyerName;
    @NotBlank
    @Size(min = 4, max = 4)
    private String lprodGu;
    
    private LprodDto lprod;
    
    private String buyerBank;
    private String buyerBankno;
    private String buyerBankname;
    private String buyerZip;
    private String buyerAdd1;
    private String buyerAdd2;
    @NotBlank
    @PhoneNumber
    private String buyerComtel;
    @NotBlank
    @PhoneNumber
    private String buyerFax;
    @NotBlank
    @Email
    private String buyerMail;

    private String buyerCharger;
    private String buyerTelext;

    // 거래품목 바인드
    private List<ProdDto> prodList;
}