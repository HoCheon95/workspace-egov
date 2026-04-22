package kr.or.ddit.mybatis;

import org.apache.ibatis.session.SqlSession;

public class SqlSessionContext {
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    
    public static void setSqlSession(SqlSession sqlSession) {
        threadLocal.set(sqlSession);
    }

    public static SqlSession getSqlSession() {
        return threadLocal.get();
    }

    public static void clearSqlSession() {
        threadLocal.remove();
    }

    public static boolean hasSqlSEssion () {
        return threadLocal.get() != null;
    }
}
