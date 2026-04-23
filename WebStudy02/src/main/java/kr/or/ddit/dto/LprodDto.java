package kr.or.ddit.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "lprodGu")
public class LprodDto {
    private Integer lprodId;
    private String lprodGu;
    private String lprodName;
}
