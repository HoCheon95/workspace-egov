package kr.or.ddit.db.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.ConnectionFactoryWithPooling;

public class JdbcTemplate {
    public final int update(
        String sql, 
        Consumer<PreparedStatement> parameterMapper
    ) {
        // 1. 쿼리 작성 **
        try (
            // 2. Connection 생성
            Connection conn = ConnectionFactoryWithPooling.createConnection();
            // 3. 쿼리 객체 새성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            // 4. 쿼리 파라미터 생성 **
            if(parameterMapper != null)
                parameterMapper.accept(pstmt);
            
            // 5. 쿼리 실행
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            // 데이터베이스 작업 중 오류가 발생하면 런타임 예외로 던져야 한다
            throw new RuntimeException(e);
        }
    }

    // 쿼리 실행 후 결과가 single row인 경우, 해당 행을 T 타입으로 바인딩하여 반환하는 메소드
    public final <T> T queryForObject(
        String sql, 
        Consumer<PreparedStatement> parameterMapper,
        Function<ResultSet, T> rowMapper 
    ) {
        List<T> list = queryForList(sql, parameterMapper, rowMapper);
        if(list.isEmpty()){
            return null;
        } else {
            return list.get(0);
        }
    }

    // 쿼리 실행 후 결과가 여러 행인 경우, 각 행을 T 타입으로 바인딩하여 List<T>로 반환하는 메소드
    public final <T> List<T> queryForList(
        String sql, 
        Consumer<PreparedStatement> parameterMapper,
        Function<ResultSet, T> rowMapper 
    ) {
        // 1. 쿼리 작성 **
        List<T> list = new ArrayList<>();
        try (
            // 2. Connection 생성
            Connection conn = ConnectionFactory.createConnection();
            // 3. 쿼리 객체 새성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 4. 쿼리 파라미터 생성 **
            if(parameterMapper != null)
                parameterMapper.accept(pstmt);
            try (
                // 5. 쿼리 실행
                ResultSet rs = pstmt.executeQuery()
            ) {
                // 6. 조회 결과 single row 바인드 **
                while (rs.next()) {
                    T one = rowMapper.apply(rs);
                    list.add(one);
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
