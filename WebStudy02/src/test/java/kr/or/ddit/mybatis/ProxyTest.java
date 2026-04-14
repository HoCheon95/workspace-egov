package kr.or.ddit.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import kr.or.ddit.member.mapper.MemberMapper;

public class ProxyTest {

    @Test
    void test() {
        SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
        Class<?> mapperType = MemberMapper.class;
        InvocationHandler handler = (noOp, method, args) -> {
            try(
                SqlSession sqlSession = sqlSessionFactory.openSession();
            ){
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperProxy, args);
            }
        };
        MemberMapper proxy = (MemberMapper) Proxy.newProxyInstance(mapperType.getClassLoader(), new Class<?>[] {mapperType}, handler);
        System.out.println(proxy.selectMemberList());
    }

    @Test
    void test2() {
        MemberMapper dao = generateMapperProxy(MemberMapper.class);
        dao.selectMemberList().forEach(System.out::println);
    }

    <T> T generateMapperProxy(Class<T> mapperType){
        SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
        InvocationHandler handler = (noOp, method, args) -> {
            try(
                SqlSession sqlSession = sqlSessionFactory.openSession();
            ){
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperProxy, args);
            }
        };
        T proxy = (T) Proxy.newProxyInstance(mapperType.getClassLoader(), new Class<?>[] {mapperType}, handler);
        return proxy;
    }
}
