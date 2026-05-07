package kr.or.ddit.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "lprodGu")
public class LprodDto implements Serializable {
    private Integer lprodId;
    private String lprodGu;
    private String lprodName;

    public Integer getLprodId() {
        return lprodId;
    }

    public void setLprodId(Integer lprodId) {
        this.lprodId = lprodId;
    }

    public String getLprodGu() {
        return lprodGu;
    }

    public void setLprodGu(String lprodGu) {
        this.lprodGu = lprodGu;
    }

    public String getLprodName() {
        return lprodName;
    }

    public void setLprodName(String lprodName) {
        this.lprodName = lprodName;
    }


}
