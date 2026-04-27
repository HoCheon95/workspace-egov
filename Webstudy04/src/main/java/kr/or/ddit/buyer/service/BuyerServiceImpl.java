package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.mapper.BuyerMapper;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.di.stereotype.Service;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    private BuyerMapper mapper;

    @Override
    public List<BuyerDto> readBuyerList() {
        return mapper.selectBuyerList();
    }

    @Override
    public BuyerDto readBuyer(String buyerId) throws EntityNotFoundException {
        
        BuyerDto buyer = mapper.selectBuyer(buyerId);
        if(buyer == null) {
            throw new EntityNotFoundException("%s 에 해당하는 제조사 레코드 없음.".formatted(buyerId));
        }
        return buyer;
    }

    @Override
    public boolean createBuyer(BuyerDto buyer) {
        int rowcnt = mapper.insertBuyer(buyer);
        return rowcnt > 0;
    }

    @Override
    public boolean modifyBuyer(BuyerDto buyer) {
        int rowcnt = mapper.updateBuyer(buyer);
        return rowcnt > 0;
    }
}
