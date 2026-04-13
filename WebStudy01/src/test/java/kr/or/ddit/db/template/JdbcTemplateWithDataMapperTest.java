package kr.or.ddit.db.template;

import org.apache.commons.lang3.function.Failable;
import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDto;

public class JdbcTemplateWithDataMapperTest {

    JdbcTemplateWithDataMapper template = new JdbcTemplateWithDataMapper();
    String sql = """
            SELECT M.MEM_ID, MEM_PASS, MEM_MILEAGE, MEM_BIR, MEM_LIKE, MEM_ADD1
                , ROLE_NAME
            FROM MEMBER M LEFT OUTER JOIN MEMBER_ROLE MR ON (M.MEM_ID = MR.MEM_ID)
                """;

    @Test
    void testQueryForList() {
        sql += "WHERE MEM_ID = ?";
        MemberDto result = template.queryForObject(sql, Failable.asConsumer(pstmt->pstmt.setString(1, "a001")), MemberDto.class);
        System.out.println(result);
    }

    @Test
    void testQueryForObject() {
        template.queryForList(sql, null, MemberDto.class).forEach(System.out::println);
    }
}
