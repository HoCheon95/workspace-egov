package kr.or.ddit.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로깅
@Service // 비즈니스 로직
@RequiredArgsConstructor // 생성자 자동 주입 
public class MemberService {

    private final MemberMapper mapper;

    public List<MemberDto> readMemberList() {
        return mapper.selectMemberList();
    }
}
