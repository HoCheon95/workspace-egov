package kr.or.ddit.step1.dao;

public class DummyDaoMysql implements IDummyDao{
    public String selectDummy() {
        return "Mysql 데이터베이스에서 Mysql 함수를 실행해 조회한 raw data";
    }
}
