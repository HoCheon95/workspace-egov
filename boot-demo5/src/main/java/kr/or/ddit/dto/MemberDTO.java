package kr.or.ddit.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자의 정보를 가진 domain layer(model):VO & Command Object:DTO
 * - VO(Value Object): 값 객체, 불변 객체(immutable object)
 * - DTO(Data Transfer Object): 계층 간 데이터 교환을 위한 객체, mutable object
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO implements Serializable {

        private int rnum;
        private String memId;
        private transient String memPass;
        private String memName;
        private transient String memRegno1;
        private transient String memRegno2;
        private transient LocalDate memBir;
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
        private transient LocalDate memMemorialday;
        private Integer memMileage;
        private boolean memDelete;
        private List<String> memRoles; // MemberDto has many role_Name

}
