package kr.or.ddit.dummy.dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.dummy.dto.DummyDto;

public class DummyDaoImplCase1Test {
    DummyDao dao = new DummyDaoImplCase1();
    @Test
    void testInsertDummy() {
        DummyDto dummy = new DummyDto();
        dummy.setCol1(1);
        dummy.setCol2("A");
        int rowcount = dao.insertDummy(dummy);
        System.out.println("rowcount = " + rowcount);
    }

    @Test
    void testSelectDummy() {
        DummyDto dummy = dao.selectDummy(1);
        System.out.println("dummy = " + dummy);
    }

    @Test
    void testSelectDummyList() {
        List<DummyDto> list = dao.selectDummyList();
        list.forEach(System.out::println);
    }
}
