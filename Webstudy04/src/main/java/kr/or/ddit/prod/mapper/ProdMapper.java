package kr.or.ddit.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.ProdDto;

/**
 * 상품 관리 persistence layer
 */
@Mapper
public interface ProdMapper {
    // selectProd
    ProdDto selectProd(@Param("prodId")String prodId);
    // selectProdList
    List<ProdDto> selectProdList();
    // insertProd
    int insertProd(ProdDto prod);
    // updateProd
    int updateProd(ProdDto prod);
    // deleteProd

}
