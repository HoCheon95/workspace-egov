package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.mapper.BuyerMapper;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class BuyerServiceImpl implements BuyerService{
    private BuyerMapper mapper = MapperProxyGenerator.generateMapperProxy(BuyerMapper.class);

    @Override
    public List<BuyerDto> readBuyerList() {
        return mapper.selectBuyerList();
    }

    @Override
    public BuyerDto readBuyer(String buyerID) throws EntityNotFoundException {
        
        BuyerDto buyer = mapper.selectBuyer(buyerID);
        if(buyer == null) {
            throw new EntityNotFoundException("%s 에 해당하는 제조사 레코드 없음.".formatted(buyerID));
        }
        return buyer;
    }
}
