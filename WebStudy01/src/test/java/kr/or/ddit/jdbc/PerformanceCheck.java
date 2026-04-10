package kr.or.ddit.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import kr.or.ddit.db.ConnectionFactory;

public class PerformanceCheck {

    // 평균 소요 시간 : 4294 ms
    @Test
    void testOneConn100Process() throws SQLException {
        long start = System.currentTimeMillis();
        
            String sql = """
                    SELECT MEM_NAME, MEM_MILEAGE
                    FROM MEMBER
                    WHERE MEM_ID = ?
                    """;
            try(
                Connection conn = ConnectionFactory.createConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ){
                String username = "a001";
                for(int i=1; i<=100; i++) {
                    pstmt.setString(1, username);
                    try(
                        ResultSet rs = pstmt.executeQuery();
                    ){
                        if(rs.next()){
                            String name = rs.getString("MEM_NAME");
                            int mileage = rs.getInt("MEM_MILEAGE") * 100;
                            System.out.printf("%s : %d\n", name, mileage);
                        }
                    }
                }
            }// try end
        
        long end = System.currentTimeMillis();
        System.out.printf("소요시간 : %d ms\n", (end-start));
    }

    // 평균 소요 시간 : 4294 ms
    @Test
    void test100Conn100Process() throws SQLException {
        long start = System.currentTimeMillis();
        for(int i=1; i<=100; i++) {
            String sql = """
                    SELECT MEM_NAME, MEM_MILEAGE
                    FROM MEMBER
                    WHERE MEM_ID = ?
                    """;
            try(
                Connection conn = ConnectionFactory.createConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ){
                String username = "a001";
                pstmt.setString(1, username);
                try(
                    ResultSet rs = pstmt.executeQuery();
                ){
                    if(rs.next()){
                        String name = rs.getString("MEM_NAME");
                        int mileage = rs.getInt("MEM_MILEAGE") * 100;
                        System.out.printf("%s : %d\n", name, mileage);
                    }
                }
            }// try end
        }
        long end = System.currentTimeMillis();
        System.out.printf("소요시간 : %d ms\n", (end-start));
    }

    // 평균 소요 시간 : 620 ms
    @Test
    void testOneConnOneProcess() throws SQLException {
        long start = System.currentTimeMillis();
        String sql = """
                SELECT MEM_NAME, MEM_MILEAGE
                FROM MEMBER
                WHERE MEM_ID = ?
                """;
        try(
            Connection conn = ConnectionFactory.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            String username = "a001";
            pstmt.setString(1, username);
            try(
                ResultSet rs = pstmt.executeQuery();
            ){
                if(rs.next()){
                    String name = rs.getString("MEM_NAME");
                    int mileage = rs.getInt("MEM_MILEAGE") * 100;
                    System.out.printf("%s : %d\n", name, mileage);
                }
            }
        }// try end
        long end = System.currentTimeMillis();
        System.out.printf("소요시간 : %d ms\n", (end-start));
    }
}
