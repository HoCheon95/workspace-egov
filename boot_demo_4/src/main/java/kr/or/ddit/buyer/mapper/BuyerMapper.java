package kr.or.ddit.buyer.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import kr.or.ddit.dto.BuyerDto;

/**
 * 제조사 관리 Persistence Layer
 */

@Mapper
public interface BuyerMapper {
    /**
     * 제조사 등록
     * 
     * @param buyerDto 등록할 제조사 정보가 담긴 DTO 객체
     * @return 등록 성공 시 1, 실패 시 0
     */
    int insertBuyer(BuyerDto buyerDto);

    /**
     * 제조사 상세 조회
     * 
     * @param buyerId 조회할 제조사 ID
     * @return BuyerDto
     */
    BuyerDto selectBuyer(@Param("buyerId") String buyerId);

    /**
     * 제조사 목록 조회
     * 
     * @return 제조사 목록
     */
    List<BuyerDto> selectBuyerList();

    /**
     * 제조사 업데이트
     * 
     * @param buyerDto 업데이트할 제조사 정보가 담긴 DTO 객체
     * @return 등록 성공 시 1, 실패 시 0
     */
    int updateBuyer(BuyerDto buyerDto);

    /**
     * 제조사 삭제
     * 
     * @param buyerId 삭제할 제조사 ID
     * @return 삭제 성공 시 1, 실패 시 0
     */
    int deleteBuyer(@Param("buyerId") String buyerId);

    List<BuyerDto> selectBuyerListByLprodGu(@Param("lprodGu") String lprodGu);

}
