package kr.or.ddit.member.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.Failable;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;

public class MemberMapperImpl implements MemberDao {
    SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();


    @Override
    public MemberDto selectMember(String username) {
        try(
            SqlSession sqlSession = sqlSessionFactory.openSession();
        ){
            return sqlSession.selectOne("Member.selectMember", username);
        }
    }

    @Override
    public List<MemberDto> selectMemberList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectMemberList'");
    }

    @Override
    public int updatePassword(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }
    
}
