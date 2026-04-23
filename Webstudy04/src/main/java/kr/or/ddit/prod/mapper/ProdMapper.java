package kr.or.ddit.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.ProdDto;

/**
 * 상품 관리 persistence layer
 */
public interface ProdMapper {
    // inserProd
    ProdDto selectProd(@Param("prodId")String prodId);
    List<ProdDto> selectProdList();
    // updateProd
    // deleteProd

}
