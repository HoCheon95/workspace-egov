package kr.or.ddit.dto;

import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.or.ddit.validate.constraints.PhoneNumber;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 제조사 관리 Domain Layer
 * 
 * Buyer (1)          Lprod (1) - 1:1
 * BuyerDto   Has A   LprodDto
 * 
 * Buyer (1)          Prod (N)
 * BuyerDto  Has Many ProdDto
 */
@Data
@EqualsAndHashCode(of = "buyerId") // buyerId만 같아도 같은 객체로 판단
public class BuyerDto implements Serializable {

    @NotBlank(groups = UpdateGroup.class)
    private String buyerId;

    @NotBlank
    private String buyerName;

    @NotBlank
    @Size(min = 4, max = 4)
    private String lprodGu;

    private LprodDto lprod; // Has A 관계

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

    // 거래 품목 바인드
    private List<ProdDto> prodList;

}
