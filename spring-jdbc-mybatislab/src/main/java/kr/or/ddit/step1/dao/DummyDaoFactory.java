package kr.or.ddit.step1.dao;

public class DummyDaoFactory {
    public static IDummyDao getDummyDao() {
        return new DummyDaoMysql();
    }
}
