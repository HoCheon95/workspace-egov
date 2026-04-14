package kr.or.ddit.mybatis;

import java.util.function.Function;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lombok.RequiredArgsConstructor;

/**
 * Template Method 패턴과 + Execute Arrount 패턴의 적용
 */
@RequiredArgsConstructor
public class MapperTemplate<S> {

    private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
    private final Class<S> mapperType;
    
    public <T> T executeArrounded (Function<S, T> callback) {
        try(
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ){
            // return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDao.selectMember", username);
            S mapperProxy = sqlSession.getMapper(mapperType);
            return callback.apply(mapperProxy);
        }
    }

}
