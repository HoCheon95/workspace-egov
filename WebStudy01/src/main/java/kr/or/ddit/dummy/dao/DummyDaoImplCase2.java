package kr.or.ddit.dummy.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ddit.db.template.DummyTemplateCase1;
import kr.or.ddit.dummy.dto.DummyDto;

public class DummyDaoImplCase2 extends DummyTemplateCase1<DummyDto> {

    @Override
    // 1. 쿼리 생성
    protected String step1() { 
        return """
                SELECT COL1, COL2 FROM DUMMY
                """;
    }

    @Override
    // 4. 쿼리 파라미터 생성
    protected void step4(PreparedStatement pstmt) throws SQLException{}

    @Override
    // 6. 조회 결과 single row 바인드
    protected DummyDto step6(ResultSet rs) throws SQLException {
        DummyDto one = new DummyDto();
        one.setCol1(rs.getInt("COL1"));
        one.setCol2(rs.getString("COL2"));
        return one;
    }

}
