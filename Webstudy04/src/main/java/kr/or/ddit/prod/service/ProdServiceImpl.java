package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.di.stereotype.Service;
import kr.or.ddit.dto.ProdDto;
import kr.or.ddit.mvc.annotation.stereotype.Autowired;
import kr.or.ddit.prod.mapper.ProdMapper;

@Service
public class ProdServiceImpl implements ProdService {

    // Inversion of Controll, Dependency Injection : 객체 생성 및 객체간 의존 관계 형성에 대한 권한을 프레임워크가 갖는 패턴
    @Autowired
    private ProdMapper mapper;
    
    /**
     * 상품 상세 조회
      * @param prodId 조회할 상품의 아이디
      * @return 조회된 상품 정보. 존재하지 않는 경우 null이 아닌 EntityNotFoundException 발생
     */
    @Override
    public ProdDto selectProd(String prodId) {
        ProdDto prod = mapper.selectProd(prodId);
        if(prod == null) {
            throw new EntityNotFoundException("%s에 해당하는 상품 레코드 없음.".formatted(prodId));
        }
        return prod;
    }

    /**
     * 상품 목록 조회
      * @return 조회된 상품 목록. 존재하지 않는 경우 빈 리스트 반환
     */
    @Override
    public List<ProdDto> selectProdList() {
        return mapper.selectProdList();
    }

    /**
     * 상품 등록
      * @param prod 등록할 상품 정보
      * @return 등록 성공 여부
     */
    @Override
    public boolean createProd(ProdDto prod) {
        return mapper.insertProd(prod) > 0;
    }

    /**
     * 상품 수정
      * @param prod 수정할 상품 정보
      * @return 수정 성공 여부
     */
    @Override
    public boolean modifyProd(ProdDto prod) {
        return mapper.updateProd(prod) > 0;
    }
}
