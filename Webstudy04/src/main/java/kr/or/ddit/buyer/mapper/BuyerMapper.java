package kr.or.ddit.buyer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.BuyerDto;

/**
 * 제조사 관리 Persistence Layer
 */
public interface BuyerMapper {
    /**
     * 신규 제조사 등록
     * @param buyer
     * @return
     */
    int insertBuyer(BuyerDto buyer);
    /**
     * 제조사 상세 조회
     * @param buyerId
     * @return 없는 경우, null 반환
     */
    BuyerDto selectBuyer(@Param("buyerId")String buyerId);

    List<BuyerDto> selectBuyerList();

    /**
     * 제조사 정보 수정
     * @param buyer
     * @return
     */
    int updateBuyer(BuyerDto buyer);
    
    void deleteBuyer();

}
