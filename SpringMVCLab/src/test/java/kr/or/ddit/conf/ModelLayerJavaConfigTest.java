package kr.or.ddit.conf;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(classes = ModelLayerJavaConfig.class)
public class ModelLayerJavaConfigTest {
    @Autowired
    DataSource dataSource;
    
    @Test
    void test() throws SQLException {
        try(
            Connection conn = dataSource.getConnection();
        ){
            log.info("connection : {}", conn);
        }
    }
}
