package kr.or.ddit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.props.dto.DataBasePropertyDto;

/**
 * JDBC (Java DataBase Connectivity)
 * 
 * 1. 데이터베이스 벤더에서 제공하는 드라이버 검색
 * 2. 드라이버를 빌드 패스에 추가
 * 3. 드라이버를 로딩
 * 4. Connection 생성
 * 5. 쿼리 객체
 * 6. 쿼리 실행
 * 7. 사용된 자원 해제(ResultSet, 쿼리객체, Connection)
 */
public class jdbcCodeTest {

    @BeforeAll // 모든 테스트 케이스 실행 전 한 번만 실행되는 메서드
    static void beforeAll() {
        System.out.println("==================== before all ====================");

    }

    @BeforeEach // 각 테스트 케이스 실행 전마다 실행되는 메서드
    void setUp() {
        System.out.println("==================== before each : 테스트 데이터 준비 ====================");
    }

    @AfterEach
    void tearDown() {
        System.out.println("==================== after each : 테스트 데이터 정리(rollback) ====================");
    }

    @Test
    void test2() {
        String sql = """
                    SELECT MEM_ID, MEM_PASS, MEM_NAME FROM MEMBER
                """;
        List<MemberDto> list = new ArrayList<>();
        try (
                Connection conn = ConnectionFactory.createConnection();
                Statement stmt = conn.createStatement();) {
            try (
                    ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(
                            MemberDto.builder()
                                    .memId(rs.getString("MEM_ID"))
                                    .memPass(rs.getString("MEM_PASS"))
                                    .memName(rs.getString("MEM_NAME"))
                                    .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 전체 프로퍼티 정보를 출력함
        list.forEach(System.out::println);
    }

    @Test
    void test() {
        String sql = """
                    SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION
                    FROM DATABASE_PROPERTIES
                """;
        List<DataBasePropertyDto> list = new ArrayList<>();
        try (
                Connection conn = ConnectionFactory.createConnection();
                Statement stmt = conn.createStatement();) {
            try (
                    ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(
                            DataBasePropertyDto.builder()
                                    .propertyName(rs.getString("PROPERTY_NAME"))
                                    .propertyValue(rs.getString("PROPERTY_VALUE"))
                                    .description(rs.getString("DESCRIPTION"))
                                    .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 전체 프로퍼티 정보를 출력함
        list.forEach(System.out::println);
    }
}