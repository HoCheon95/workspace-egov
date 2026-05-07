package kr.or.ddit.step6.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.step6.dto.DataBasePropertyDto;

@Repository // DB접근
public class Step6Dao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<DataBasePropertyDto> selectDataBaseProperties() {
        String sql = """
                SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION
                FROM DATABASE_PROPERTIES                
                """;
        List<DataBasePropertyDto> list = jdbcTemplate.query(sql, (rs, rownum) -> {
                DataBasePropertyDto dto = new DataBasePropertyDto();
                dto.setPROPERTY_NAME(rs.getString("PROPERTY_NAME"));
                dto.setPROPERTY_VALUE(rs.getString("PROPERTY_VALUE"));
                dto.setDESCRIPTION(rs.getString("DESCRIPTION"));
                return dto;
        });
        return list;
    }
}
