package kr.or.ddit.step6.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.step6.dto.DataBasePropertyDto;

@Mapper
public interface Step6Mapper {
    public List<DataBasePropertyDto> selectDataBaseProperties();
}
