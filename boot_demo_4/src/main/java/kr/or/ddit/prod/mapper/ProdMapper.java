package kr.or.ddit.prod.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.ProdDto;

@Mapper
public interface ProdMapper {
    ProdDto selectProd(String prodId);

    int selectTotalRecord(PaginationInfo<?> paginationInfo);

    List<ProdDto> selectProdList(PaginationInfo<?> paginationInfo);

    int insertProd(ProdDto prodDto);

    int updateProd(ProdDto prodDto);
}
