package kr.or.ddit.prod.service;

import java.util.List;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.ProdDto;

public interface ProdService {
    boolean createProd(ProdDto prodDto);

    ProdDto readProd(String prodId) throws EntityNotFoundException;

    List<ProdDto> readProdList(PaginationInfo<?> paginationInfo);

    boolean modifyProd(ProdDto prodDto);
}
