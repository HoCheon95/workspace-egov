package kr.or.ddit.step1.dao;

public class DummyDaoOracle implements IDummyDao{
    public String selectDummy() {
        return "oracle 데이터베이스에서 oracle 함수를 실행해 조회한 raw data";
    }
}
