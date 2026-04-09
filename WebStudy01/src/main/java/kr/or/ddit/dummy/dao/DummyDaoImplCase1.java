package kr.or.ddit.dummy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.dummy.dto.DummyDto;

public class DummyDaoImplCase1 implements DummyDao {

    @Override
    public int insertDummy(DummyDto dummy) {
        // 1. 쿼리 작성 **
        // 사용자 ID로 회원 정보와 해당 회원의 권한들을 함께 조회하는 SQL 쿼리문이다
        String sql = """
                    INSERT INTO DUMMY
                    (COL1, COL2)
                    VALUES(?, ?)
                """;
        try (
            // 2. Connection 생성
            // 데이터베이스 연결을 생성하고 쿼리를 실행할 준비를 한다
            Connection conn = ConnectionFactory.createConnection();
            // 3. 쿼리 객체 새성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            // 4. 쿼리 파라미터 생성 **
            // 쿼리문의 물음표(?) 자리에 조회할 사용자 아이디를 채워 넣어야 한다
            pstmt.setInt(1, dummy.getCol1());
            pstmt.setString(2, dummy.getCol2());
            // 5. 쿼리 실행
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            // 데이터베이스 작업 중 오류가 발생하면 런타임 예외로 던져야 한다
            throw new RuntimeException(e);
        }
    }

    @Override
    public DummyDto selectDummy(int col1) {
        // 1. 쿼리 작성 **
        // 사용자 ID로 회원 정보와 해당 회원의 권한들을 함께 조회하는 SQL 쿼리문이다
        String sql = """
                    SELECT COL1, COL2
                    FROM DUMMY
                    WHERE COL1 = ?
                """;
        DummyDto one = null;
        try (
                // 2. Connection 생성
                // 데이터베이스 연결을 생성하고 쿼리를 실행할 준비를 한다
                Connection conn = ConnectionFactory.createConnection();
                // 3. 쿼리 객체 새성
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ){
                // 4. 쿼리 파라미터 생성 **
                pstmt.setInt(1, col1);
                // 쿼리문의 물음표(?) 자리에 조회할 사용자 아이디를 채워 넣어야 한다
            try (
                // 5. 쿼리 실행
                // 데이터베이스에서 조회된 결과 데이터를 받아온다
                ResultSet rs = pstmt.executeQuery()
            ) {
                // 6. 조회 결과 single row 바인드 **
                if (rs.next()) {
                    one = new DummyDto();
                    one.setCol1(rs.getInt("COL1"));
                    one.setCol2(rs.getString("COL2"));
                }
                return one;
            }
        } catch (SQLException e) {
            // 데이터베이스 작업 중 오류가 발생하면 런타임 예외로 던져야 한다
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DummyDto> selectDummyList() {
        // 1. 쿼리 작성 **
        // 사용자 ID로 회원 정보와 해당 회원의 권한들을 함께 조회하는 SQL 쿼리문이다
        String sql = """
                    SELECT COL1, COL2
                    FROM DUMMY
                """;
        List<DummyDto> list = new ArrayList<>();
        try (
                // 2. Connection 생성
                // 데이터베이스 연결을 생성하고 쿼리를 실행할 준비를 한다
                Connection conn = ConnectionFactory.createConnection();
                // 3. 쿼리 객체 새성
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            // 4. 쿼리 파라미터 생성 **
            // 쿼리문의 물음표(?) 자리에 조회할 사용자 아이디를 채워 넣어야 한다
            try (
                    // 5. 쿼리 실행
                    // 데이터베이스에서 조회된 결과 데이터를 받아온다
                    ResultSet rs = pstmt.executeQuery()) {
                // 6. 조회 결과 single row 바인드 **
                while (rs.next()) {
                    DummyDto one = new DummyDto();
                    one.setCol1(rs.getInt("COL1"));
                    one.setCol2(rs.getString("COL2"));
                    list.add(one);
                }
                return list;
            }
        } catch (SQLException e) {
            // 데이터베이스 작업 중 오류가 발생하면 런타임 예외로 던져야 한다
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteDummy(int col1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDummy'");
    }
}
