package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.LprodDto;

public interface LprodService {

    List<LprodDto> readLprodList(PaginationInfo<?> paginationInfo);

}
