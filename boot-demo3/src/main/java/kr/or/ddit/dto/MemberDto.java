package kr.or.ddit.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(of = "memId")
@Data
public class MemberDto implements Serializable{
    private String memId;
    @ToString.Exclude
    @JsonIgnore
    private String memPass;
    private String memName;
    @ToString.Exclude
    @JsonIgnore
    private transient String memRegno1;
    @ToString.Exclude
    @JsonIgnore
    private transient String memRegno2;
    private LocalDate memBir;
    private String memZip;
    private String memAdd1;
    private String memAdd2;
    private String memHometel;
    private String memComtel;
    private String memHp;
    private String memMail;
    private String memJob;
    private String memLike;
    private String memMemorial;
    private LocalDate memMemorialday;
    private Integer memMileage;
    private String memDelete;
}
