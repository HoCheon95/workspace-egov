package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.dto.ProdDto;

public interface BuyerService {
    boolean createBuyer(BuyerDto buyerDto);

    BuyerDto readBuyer(String buyerId);

    List<BuyerDto> readBuyerList();

    List<BuyerDto> readBuyerListByLprodGu(String lprodGu);

    boolean modifyBuyer(BuyerDto buyerDto);

    boolean removeBuyer(String buyerId);

    List<BuyerDto> readBuyerListset(PaginationInfo<ProdDto> paginationInfo);


}
