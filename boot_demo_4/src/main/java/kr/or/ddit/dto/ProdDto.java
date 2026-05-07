package kr.or.ddit.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "prodId")
public class ProdDto {

        private int rnum;


        @NotBlank(groups = UpdateGroup.class, message = "상품 아이디는 수정 시 필수입니다.")
        @Size(min = 10, max = 10, groups = UpdateGroup.class,
                        message = "상품 아이디는 {min}자리여야 합니다. 입력된 값: ${validatedValue}")
        @Pattern(regexp = "^[A-Za-z0-9]{10}$", groups = UpdateGroup.class,
                        message = "상품 아이디 형식이 올바르지 않습니다. 입력된 값: ${validatedValue}")
        private String prodId;

        @NotBlank(groups = {InsertGroup.class, UpdateGroup.class}, message = "상품명은 필수 입력입니다.")
        @Size(max = 40, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "상품명은 최대 {max}자까지 입력 가능합니다. 입력된 값: ${validatedValue}")
        private String prodName;

        @NotBlank(groups = {InsertGroup.class, UpdateGroup.class}, message = "상품 분류 코드는 필수 입력입니다.")
        @Size(min = 4, max = 4, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "상품 분류 코드는 {min}자리여야 합니다. 입력된 값: ${validatedValue}")
        private String lprodGu;

        private LprodDto lprod;

        @NotBlank(groups = {InsertGroup.class, UpdateGroup.class}, message = "제조사 코드는 필수 입력입니다.")
        @Size(min = 6, max = 6, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "제조사 코드는 {min}자리여야 합니다. 입력된 값: ${validatedValue}")
        private String buyerId;
        private BuyerDto buyer;

        @NotNull(groups = {InsertGroup.class, UpdateGroup.class}, message = "구매가는 필수 입력입니다.")
        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "구매가는 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodCost;

        @NotNull(groups = {InsertGroup.class, UpdateGroup.class}, message = "판매가는 필수 입력입니다.")
        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "판매가는 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodPrice;

        @NotNull(groups = {InsertGroup.class, UpdateGroup.class}, message = "세일가는 필수 입력입니다.")
        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "세일가는 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodSale;

        @NotBlank(groups = {InsertGroup.class, UpdateGroup.class}, message = "상품 한줄 요약은 필수 입력입니다.")
        @Size(max = 100, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "상품 한줄 요약은 최대 {max}자까지 입력 가능합니다. 입력된 값: ${validatedValue}")
        private String prodOutline;

        private String prodDetail;

        @Size(max = 40, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "상품 이미지명은 최대 {max}자까지 입력 가능합니다. 입력된 값: ${validatedValue}")
        private String prodImg;

        @NotNull(groups = {InsertGroup.class, UpdateGroup.class}, message = "총재고는 필수 입력입니다.")
        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "총재고는 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodTotalstock;

        @PastOrPresent(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "입고일은 현재 또는 과거 날짜여야 합니다. 입력된 값: ${validatedValue}")
        private LocalDate prodInsdate;

        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "적정재고는 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodProperstock;

        @Size(max = 20, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "규격은 최대 {max}자까지 입력 가능합니다. 입력된 값: ${validatedValue}")
        private String prodSize;

        @Size(max = 20, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "색상은 최대 {max}자까지 입력 가능합니다. 입력된 값: ${validatedValue}")
        private String prodColor;

        @Size(max = 255, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "배송정보는 최대 {max}자까지 입력 가능합니다. 입력된 값: ${validatedValue}")
        private String prodDelivery;

        @Size(max = 6, groups = {InsertGroup.class, UpdateGroup.class},
                        message = "단위는 최대 {max}자까지 입력 가능합니다. 입력된 값: ${validatedValue}")
        private String prodUnit;

        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "입고수량은 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodQtyin;

        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "판매수량은 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodQtysale;

        @PositiveOrZero(groups = {InsertGroup.class, UpdateGroup.class},
                        message = "마일리지는 0 이상이어야 합니다. 입력된 값: ${validatedValue}")
        private Integer prodMileage;
}
