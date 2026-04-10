package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.Failable;

import kr.or.ddit.db.template.JdbcTemplate;
import kr.or.ddit.member.dto.MemberDto;

public class MemberDaoImpl implements MemberDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public List<MemberDto> selectMemberList() {
        // 사용자 ID로 회원 정보와 해당 회원의 권한들을 함께 조회하는 SQL 쿼리문이다
        String sql = """
            SELECT M.MEM_ID, MEM_PASS, MEM_NAME, ROLE_NAME
            FROM MEMBER M LEFT OUTER JOIN MEMBER_ROLE MR ON(M.MEM_ID = MR.MEM_ID)
        """;
        return jdbcTemplate.queryForList(sql, null, Failable.asFunction(rs->{
            String roleName = rs.getString("ROLE_NAME");
            MemberDto member = MemberDto.builder()
                        .memId(rs.getString("MEM_ID"))
                        .memPass(rs.getString("MEM_PASS"))
                        .memName(rs.getString("MEM_NAME"))
                        .memRoles(new ArrayList<>())
                        .build();
                
            // 한 명의 회원이 여러 권한을 가질 수 있으므로, 조회된 권한명을 리스트에 계속 추가한다
            if (StringUtils.isNotBlank(roleName)) {
                member.getMemRoles().add(roleName);
            }
            return member;
        }));
    }

    // n 1 ~ 7 번까지 단계중, 1, 4, 6번에서 차이가 발생함.
    @Override
    public MemberDto selectMember(String username) {
        // 사용자 ID로 회원 정보와 해당 회원의 권한들을 함께 조회하는 SQL 쿼리문이다
        String sql = """
            SELECT M.MEM_ID, MEM_PASS, MEM_NAME, ROLE_NAME
            FROM MEMBER M LEFT OUTER JOIN MEMBER_ROLE MR ON(M.MEM_ID = MR.MEM_ID)
            WHERE M.MEM_ID = ?
        """;
        List<MemberDto> list = jdbcTemplate.queryForList(sql, Failable.asConsumer(pstmt->{
            pstmt.setString(1, username);
        }), Failable.asFunction(rs->{
            String roleName = rs.getString("ROLE_NAME");
            MemberDto member = MemberDto.builder()
                        .memId(rs.getString("MEM_ID"))
                        .memPass(rs.getString("MEM_PASS"))
                        .memName(rs.getString("MEM_NAME"))
                        .memRoles(new ArrayList<>())
                        .build();
                
            // 한 명의 회원이 여러 권한을 가질 수 있으므로, 조회된 권한명을 리스트에 계속 추가한다
            if (StringUtils.isNotBlank(roleName)) {
                member.getMemRoles().add(roleName);
            }
            return member;
        }));

        if(list.isEmpty()){
            return null;
        } else {
            // 하나의 MemberDto를 만들고, 여러 레코드의 role_name 을 병합함.
            List<String> memRoles = list.stream().flatMap(mdto->mdto.getMemRoles().stream()).toList();
            MemberDto one = list.getFirst();
            one.setMemRoles(memRoles);
            return one;
        }
    }

    public MemberDto selectMemberDummy(String username, String password) {
        String sql = """
                    SELECT MEM_ID, MEM_PASS, MEM_NAME
                    FROM MEMBER
                    WHERE MEM_ID = ? AND MEM_PASS = ?
                """;
        return jdbcTemplate.queryForObject(sql, Failable.asConsumer(pstmt->{
            pstmt.setString(1, username);
            pstmt.setString(2, password);
        }), Failable.asFunction(rs->{
            return MemberDto.builder()
                    .memId(rs.getString("MEM_ID"))
                    .memPass(rs.getString("MEM_PASS"))
                    .memName(rs.getString("MEM_NAME"))
                    .build();
        }));
    }

    @Override
    public int updatePassword(String username, String password) {
        String sql = """
                UPDATE MEMBER
                SET MEM_PASS = ?
                WHERE MEM_ID = ?
                """;
        return jdbcTemplate.update(sql, Failable.asConsumer(pstmt->{
            pstmt.setString(1, password);
            pstmt.setString(2, username);
        }));
    }
}
