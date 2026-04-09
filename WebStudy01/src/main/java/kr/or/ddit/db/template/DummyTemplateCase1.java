package kr.or.ddit.db.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;

public abstract class DummyTemplateCase1<T> {
    protected abstract String step1();
    protected abstract void step4(PreparedStatement pstmt) throws SQLException;
    protected abstract T step6(ResultSet rs) throws SQLException;

    public final List<T> queryTemplate() {
        // 1. 쿼리 작성 **
        String sql = step1();
        List<T> list = new ArrayList<>();
        try (
            // 2. Connection 생성
            Connection conn = ConnectionFactory.createConnection();
            // 3. 쿼리 객체 새성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 4. 쿼리 파라미터 생성 **
            step4(pstmt);
            try (
                // 5. 쿼리 실행
                ResultSet rs = pstmt.executeQuery()
            ) {
                // 6. 조회 결과 single row 바인드 **
                while (rs.next()) {
                    T one = step6(rs);
                    list.add(one);
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
