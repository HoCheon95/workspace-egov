package kr.or.ddit.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"cartNo", "prodId"})
public class CartDto {
    private String memId;
    private String cartNo;
    private String prodId;
    private Integer cartQty;
    private LocalDate cartDate;

    private ProdDto prod; // CartDto has a ProdDto
}
