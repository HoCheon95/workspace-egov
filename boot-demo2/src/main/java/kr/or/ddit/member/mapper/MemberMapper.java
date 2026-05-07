package kr.or.ddit.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.MemberDto;

@Mapper
public interface MemberMapper {
    
    public List<MemberDto> selectMemberList();
}
