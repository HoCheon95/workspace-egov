package kr.or.ddit.dummy.dao;

import java.util.List;

import org.apache.commons.lang3.function.Failable;

import kr.or.ddit.db.template.JdbcTemplate;
import kr.or.ddit.dummy.dto.DummyDto;

public class DummyDaoImpleCase3 implements DummyDao {

    JdbcTemplate tmpl = new JdbcTemplate();

    @Override
    public int insertDummy(DummyDto dummy) {
        String sql = """
                INSERT INTO DUMMY
                (COL1, COL2)
                VALUES(?, ?)
                """;
        return tmpl.update(sql, Failable.asConsumer(pstmt -> {
            pstmt.setInt(1, dummy.getCol1());
            pstmt.setString(2, dummy.getCol2());
        }));
    }

    @Override
    public DummyDto selectDummy(int col1) {
        String sql = """
                SELECT COL1, COL2 FROM DUMMY
                WHERE COL1 = ?
                """;
        return tmpl.queryForObject(
                sql,
                Failable.asConsumer(pstmt->pstmt.setInt(1, col1)),
                Failable.asFunction(rs->{
                DummyDto one = new DummyDto();
                one.setCol1(rs.getInt("COL1"));
                one.setCol2(rs.getString("COL2"));
                return one;
            })
        );
    }

    @Override
    public List<DummyDto> selectDummyList() {
        String sql = """
                SELECT COL1, COL2 FROM DUMMY
                """;
        return tmpl.queryForList(sql, pstmt -> {
        }, Failable.asFunction(rs -> {
            DummyDto one = new DummyDto();
            one.setCol1(rs.getInt("COL1"));
            one.setCol2(rs.getString("COL2"));
            return one;
        }));
    }

    @Override
    public int deleteDummy(int col1) {
        String sql = """
                DELETE FROM DUMMY
                WHERE COL1 = ?
                """;
        return tmpl.update(sql, Failable.asConsumer(pstmt->{
            pstmt.setInt(1, col1);
        }));
    }

}
