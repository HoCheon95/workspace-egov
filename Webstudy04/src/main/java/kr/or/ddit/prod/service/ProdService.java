package kr.or.ddit.prod.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.dto.ProdDto;

/**
 * 제품 관리 Business Logic Layer
 */
public interface ProdService {
    /**
     * 상품 상세 조회
     * @param prodId
     * @return
     * @throws EntityNotFoundException
     */
    ProdDto selectProd(@Param("prodId") String prodId) throws EntityNotFoundException;

    /**
     * 상품 목록 조회
     * @return
     */
    List<ProdDto> selectProdList();

    /**
     * 상품 등록
     * @param prod
     * @return
     */
    boolean createProd(ProdDto prod);

    /**
     * 상품 수정
     * @param prod
     * @return
     */
    boolean modifyProd(ProdDto prod);
}
