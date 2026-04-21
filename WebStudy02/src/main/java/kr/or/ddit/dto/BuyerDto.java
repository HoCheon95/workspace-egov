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
 * BUYER(1)       LPROD(1) - 1:1
 * BuyerDto  Has  LprodDto
 * 
 * BUYER(1)            PROD(N)
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