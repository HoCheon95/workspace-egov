package kr.or.ddit.lprod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.LprodDto;
import kr.or.ddit.lprod.mapper.LprodMapper;

@Service
public class LprodServiceImpl implements LprodService{

    @Autowired
    private LprodMapper lprodMapper;


    @Override
    public List<LprodDto> readLprodList(PaginationInfo<?> paginationInfo) {
        return lprodMapper.selectLprodList();
    }

}
