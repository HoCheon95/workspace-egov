package kr.or.ddit.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MapperProxyGenerator {
    private static final SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
    
    public static <T> T generateMapperProxy(Class<T> mapperType) {
        InvocationHandler handler = (noOp, method, args) -> {
            if(SqlSessionContext.hasSqlSEssion()) {
                SqlSession sqlSession = SqlSessionContext.getSqlSession();
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperType, args);
            }
            try (
                    SqlSession sqlSession = sqlSessionFactory.openSession(true);
            ) {
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperProxy, args);
            }
        };
        T proxy = (T) Proxy.newProxyInstance(mapperType.getClassLoader(), new Class<?>[] { mapperType }, handler);
        return proxy;
    }
}
