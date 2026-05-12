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
import kr.or.ddit.validate.constraints.PhoneNumber;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
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

        @NotBlank(groups = {InsertGroup.class, DeleteGroup.class}, message = "회원 아이디는 필수 입력입니다.")
        @Size(min = 4, max = 15, groups = {InsertGroup.class, DeleteGroup.class},
                        message = "회원 아이디는 {min}~{max}자 사이여야 합니다. 입력된 값: ${validatedValue}")
        private String memId;

        @NotBlank(groups = DeleteGroup.class, message = "회원 패스워드는 필수 입력입니다.")
        @Size(min = 4, max = 12)
        // @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[\\d])(?=.*[@!#$%^&*])[\\w@!#$%^&*]{4,12}$",
        //                 message = "회원 패스워드는 영문자, 숫자, 특수문자 조합으로 4~12자 사이여야 합니다. 입력된 값: ${validatedValue}")
        // (?=.*[a-zA-Z]) : 영문자 최소 1개 이상
        // (?=.*[\\d]) : 숫자 최소 1개 이상
        // (?=.*[@!#$%^&*]) : 특수문자 최소 1개 이상
        // [\\w@!#$%^&*]{4,12} : 영문자, 숫자, 특수문자 조합으로 4~12자 사이
        private transient String memPass;

        @NotBlank
        private String memName;

        private transient String memRegno1;
        private transient String memRegno2;

        @Past
        private transient LocalDate memBir;

        @NotBlank
        private String memZip;

        @NotBlank
        private String memAdd1;

        @NotBlank
        private String memAdd2;

        @PhoneNumber
        private String memHometel;

        @PhoneNumber
        private String memComtel;

        @PhoneNumber(regex = "010-\\d{3,4}-\\d{4}", displayPattern = "010-1234-5678")
        private String memHp;

        @NotBlank
        @Email
        private String memMail;

        private String memJob;
        private String memLike;
        private String memMemorial;

        private transient LocalDate memMemorialday;

        @PositiveOrZero
        private Integer memMileage;
        private boolean memDelete;
        private List<String> memRoles; // MemberDto has many role_Name

        // 구매이력
        private transient Set<CartDto> cartList; // MemberDto has many CartDto + ProdDto

}
