package kr.or.ddit.buyer.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.dto.BuyerDto;

/**
 * 제조사 관리 Business Logic Layer
 */
public interface BuyerService {
    /**
     * 거래처 정보 수정
     * @param member
     */
    boolean modifyBuyer(BuyerDto member);


    boolean createBuyer(BuyerDto buyer);

    /**
     * 
     * @param buyerID
     * @return
     * @throws EntityNotFoundException 
     */
    BuyerDto readBuyer(@Param("buyerId") String buyerId) throws EntityNotFoundException;

    /**
     * 
     * @return
     */
    List<BuyerDto> readBuyerList();
    // modifyBuyer
    // removeBuyer
}
