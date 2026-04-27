package kr.or.ddit.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "lprodGu")
public class LprodDto implements Serializable{
    private Integer lprodId;
    private String lprodGu;
    private String lprodName;
}
