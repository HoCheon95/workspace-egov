package com.example.demo.lprod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.LprodDto;


@Mapper
public interface LprodMapper {
    
    public List<LprodDto> selectLprodList();
}
