package kr.or.ddit.step7.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.step7.dto.LprodDto;

@Mapper
public interface LprodMapper {
    
    public List<LprodDto> selectLprodList();
}
