package kr.or.ddit.buyer.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerMapperTest {
    BuyerMapper mapper = MapperProxyGenerator.generateMapperProxy(BuyerMapper.class);

    @Test
    void testInsertBuyer() {
       
    }

    @Test
    void testSelecttBuyer() {
        BuyerDto buyer = mapper.selectBuyer("P10101");
        assertNotNull(buyer);
        log.info("{}, {}", buyer.getBuyerName(), buyer.getProdList());
    }

    @Test
    void testSelectBuyerList() {
        mapper.selectBuyerList().forEach(b -> log.info("{}", b));
    }

    @Test
    void updateBuyer(){

    }

    @Test
    void deleteBuyer(){

    }
}
