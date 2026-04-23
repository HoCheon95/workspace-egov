package kr.or.ddit.prod.mapper;

import org.junit.jupiter.api.Test;

import kr.or.ddit.dto.ProdDto;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdMapperTest {

    ProdMapper mapper = MapperProxyGenerator.generateMapperProxy(ProdMapper.class);

    @Test
    void testSelectProd() {
        ProdDto prod = mapper.selectProd("P101000001");
        log.info("{}", prod);
        log.info("{}", prod.getLprod());
        log.info("{}", prod.getBuyer());
    }

    @Test
    void testSelectProdList() {
        mapper.selectProdList()
                .forEach(p->{
                    log.info("{}, {}, {}", p.getProdName(), p.getLprod().getLprodName(), p.getBuyer().getBuyerName());
                });
    }
}
