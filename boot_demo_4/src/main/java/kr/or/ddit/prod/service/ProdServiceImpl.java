package kr.or.ddit.prod.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.ProdDto;

import kr.or.ddit.prod.mapper.ProdMapper;

@Service
public class ProdServiceImpl implements ProdService {
    @Autowired
    private ProdMapper prodMapper;

    @Override
    public boolean createProd(ProdDto prodDto) {
        int cnt = prodMapper.insertProd(prodDto);
        return cnt > 0;
    }

    @Override
    public boolean modifyProd(ProdDto prodDto) {
        int cnt = prodMapper.updateProd(prodDto);
        return cnt > 0;
    }

    @Override
    public ProdDto readProd(String prodId) {
        ProdDto prodDto = prodMapper.selectProd(prodId);
        if (prodDto == null) {
            throw new EntityNotFoundException("%s 상품의 정보가 없습니다.".formatted(prodId));
        } else {
            return prodDto;
        }
    }

    @Override
    public List<ProdDto> readProdList(PaginationInfo<?> paginationInfo) {
        int totalRecord = prodMapper.selectTotalRecord(paginationInfo);
        paginationInfo.setTotalRecord(totalRecord);

        return prodMapper.selectProdList(paginationInfo);
    }

}
