package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
    // SqlSessionFactory는 sql 하나당 하나씩 존재
    private static final SqlSessionFactory sqlSessionFactory;
    static {
        String configFile = "kr/or/ddit/mybatis/Configuration.xml";
        try(
            Reader reader = Resources.getResourceAsReader(configFile);
        ) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
