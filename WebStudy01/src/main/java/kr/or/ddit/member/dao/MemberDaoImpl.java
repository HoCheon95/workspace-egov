package kr.or.ddit.member.dao;

import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.dto.MemberDto;

public class MemberDaoImpl implements MemberDao {

    @Override
    public MemberDto selectMember(String username) {
        String sql = """
            SELECT M.MEM_ID, MEM_PASS, MEM_NAME, ROLE_NAME
            FROM MEMBER M LEFT OUTER JOIN MEMBER_ROLE MR ON(M.MEM_ID = MR.MEM_ID)
            WHERE M.MEM_ID = ?
        """;
        MemberDto member = null;
        try (
            Connection conn = ConnectionFactory.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, username);
            try (
                ResultSet rs = pstmt.executeQuery()
            ) {
                while (rs.next()) {
                    String roleName = rs.getString("ROLE_NAME");
                    if(member==null){
                        member = MemberDto.builder()
                                .memId(rs.getString("MEM_ID"))
                                .memPass(rs.getString("MEM_PASS"))
                                .memName(rs.getString("MEM_NAME"))
                                .memRoles(new ArrayList<>())
                                .build();
                        
                    }
                    if (StringUtils.isNotBlank(roleName)) {
                        member.getMemRoles().add(roleName);
                    }
                }
                return member;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MemberDto selectMemberDummy(String username, String password) {
        String sql = """
                    SELECT MEM_ID, MEM_PASS, MEM_NAME
                    FROM MEMBER
                    WHERE MEM_ID = ? AND MEM_PASS = ?
                """;
        MemberDto member = null;
        try (
            Connection conn = ConnectionFactory.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (
                ResultSet rs = pstmt.executeQuery();
            ) {
                if (rs.next()) {

                    member = MemberDto.builder()
                            .memId(rs.getString("MEM_ID"))
                            .memPass(rs.getString("MEM_PASS"))
                            .memName(rs.getString("MEM_NAME"))
                            .build();
                }
                return member;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
