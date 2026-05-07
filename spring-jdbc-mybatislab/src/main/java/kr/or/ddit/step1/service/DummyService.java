package kr.or.ddit.step1.service;

import kr.or.ddit.step1.dao.IDummyDao;

public class DummyService {
    // 1. 의존 객체를 new 키워드와 생서앚로 직접 생성하는 방식 : 결합력 최상
    // private IDummyDao dao = new DummyDaoMysql();

    // 2. Factory Method Pattern : 결합력 잔존 및 분산
    // private IDummyDao dao = DummyDaoFactory.getDummyDao();

    // 3. Strategy Pattern : 전략의 주입자가 필요함.
    private IDummyDao dao;
    
    public DummyService(IDummyDao dao) {
        super();
        this.dao = dao;
    }

    public void setDao(IDummyDao dao) {
        this.dao = dao;
    }

    public String readDummy() {
        return dao.selectDummy() + "를 가공해 만든 information";
    }
}
