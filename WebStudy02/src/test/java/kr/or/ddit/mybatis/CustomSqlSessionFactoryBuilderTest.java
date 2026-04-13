package kr.or.ddit.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

public class CustomSqlSessionFactoryBuilderTest {
    @Test
    void testGetSqlSessionFactory() {
        SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
        System.out.println(factory);

    }
}
