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
import kr.or.ddit.validate.contraints.PhoneNumber;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자의 정보를 가진 domain layer(Model) & CommandObject
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDto implements Serializable{
    @NotBlank(groups = {InsertGroup.class, DeleteGroup.class}, message = "아이디 필수 입력")
    @Size(max = 15, min = 4, groups = {InsertGroup.class, DeleteGroup.class}, message = "입력값(${validatedValue}), 길이는 {MIN}~{MAX}글자")
    private String memId;
    @NotBlank
    @Size(min = 4, max = 12)
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[\\d])(?=.*[@!#])[\\w@!#]{4,12}$")
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
    @PhoneNumber(regex = "010-\\d{3,4}-\\d{4}", displayPtrn = "010-0000-0000")
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
    private List<String> memRoles; // MemberDto has many roleName

    // 구매이력
    private transient Set<CartDto> cartList; // MembetDto has many CartDto + ProdDto
}
