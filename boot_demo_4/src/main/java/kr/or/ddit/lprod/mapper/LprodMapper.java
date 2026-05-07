package kr.or.ddit.lprod.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.dto.LprodDto;

@Mapper
public interface LprodMapper {
    List<LprodDto> selectLprodList();
}

