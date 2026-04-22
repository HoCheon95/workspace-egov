package kr.or.ddit.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "prodId")
public class ProdDto {
    private String prodId;
    private String prodName;

    private String lprodGu;
    private LprodDto lprod; // ProdDto has a LprodDto
    private String buyerId;
    private BuyerDto buyer; // ProdDto has a BuyerDto

    private Integer prodCost;
    private Integer prodPrice;
    private Integer prodSale;
    private String prodOutline;
    private String prodDetail;
    private String prodImg;
    private Integer prodTotalstock;
    private LocalDate prodInsdate;
    private Integer prodProperstock;
    private String prodSize;
    private String prodColor;
    private String prodDelivery;
    private String prodUnit;
    private Integer prodQtyin;
    private Integer prodQtysale;
    private Integer prodMileage;
}
