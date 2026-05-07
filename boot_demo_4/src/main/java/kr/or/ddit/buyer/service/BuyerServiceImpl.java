package kr.or.ddit.buyer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.ddit.buyer.mapper.BuyerMapper;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.dto.ProdDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerMapper buyerMapper;

    @Override
    public BuyerDto readBuyer(String buyerId) {
        BuyerDto buyerDto = buyerMapper.selectBuyer(buyerId);
        if (buyerDto == null) {
            throw new EntityNotFoundException("존재하지 않는 id 입니다. id: %s".formatted(buyerId));
        }
        return buyerDto;
    }

    @Override
    public List<BuyerDto> readBuyerList() {
        return buyerMapper.selectBuyerList();
    }


    @Override
    public boolean createBuyer(BuyerDto buyerDto) {
        int cnt = buyerMapper.insertBuyer(buyerDto);
        return cnt > 0;
    }

    @Override
    public boolean modifyBuyer(BuyerDto buyerDto) {
        int cnt = buyerMapper.updateBuyer(buyerDto);
        return cnt > 0;
    }

    @Override
    public boolean removeBuyer(String buyerId) {
        if (buyerMapper.selectBuyer(buyerId) != null) {
            int cnt = buyerMapper.deleteBuyer(buyerId);
            return cnt > 0;
        } else {
            return false;
            // throw new IllegalArgumentException("존재하지 않는 제조사 입니다.");
        }
    }

    @Override
    public List<BuyerDto> readBuyerListByLprodGu(String lprodGu) {
        return buyerMapper.selectBuyerListByLprodGu(lprodGu);
    }

    @Override
    public List<BuyerDto> readBuyerListset(PaginationInfo<ProdDto> paginationInfo) {
        return buyerMapper.selectBuyerList();
    }
}
