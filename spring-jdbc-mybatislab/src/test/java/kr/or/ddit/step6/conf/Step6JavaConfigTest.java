package kr.or.ddit.step6.conf;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.step6.dto.DataBasePropertyDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(classes =  Step6JavaConfig.class)
public class Step6JavaConfigTest {
    
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testJdbcTemplate() {
        String sql = """
                SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION
                FROM DATABASE_PROPERTIES                
                """;
        List<DataBasePropertyDto> list = jdbcTemplate.query(sql, (rs, rownum) -> {
                DataBasePropertyDto dto = new DataBasePropertyDto();
                dto.setPROPERTY_NAME(rs.getString("PROPERTY_NAME"));
                dto.setPROPERTY_VALUE(rs.getString("PROPERTY_VALUE"));
                dto.setDESCRIPTION(rs.getString("DESCRIPTION"));
                log.info("{}", dto);
                return dto;
        });
        list.forEach(dto -> log.info("{}", dto));
    }

    @Test
    void systemPropertyTest() {
        System.getProperties()
            .forEach((k, v) -> log.info("{}:{}", k, v));
    }

    @Test
    void test() throws SQLException{
        // dataSource 로부터 커넥션을 생성하고,
        // 해당 커넥션을 로그로 출력할 것.
        try(
            Connection conn = dataSource.getConnection();
        ){
            log.info("{}", conn);
        }
    }
}
