package kr.or.ddit.member.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dto.MemberDto;

public class MemberDaoInMemoryImpl implements MemberDao{
    private final Map<String, MemberDto> memberTable;
    {
        memberTable = new LinkedHashMap<>();
        memberTable.put("a001", MemberDto.builder()
                                .memId("a001")
                                .memPass("java")
                                .memName("김은대")
                                .memRoles(List.of("ROLE_ADMIN", "ROLE_USER"))
                                .build());
        memberTable.put("b001", MemberDto.builder()
                                .memId("b001")
                                .memPass("java")
                                .memName("신용환")
                                .memRoles(List.of("ROLE_USER"))
                                .build());
    }

    @Override
    public MemberDto selectMember(String username) {
        return memberTable.get(username);
    }

    @Override
    public List<MemberDto> selectMemberList() {
        return memberTable.values().stream().toList();
    }

    @Override
    public int updatePassword(String username, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }
    
}
