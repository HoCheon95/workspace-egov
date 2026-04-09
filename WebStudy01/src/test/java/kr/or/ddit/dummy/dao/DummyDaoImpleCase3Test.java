package kr.or.ddit.dummy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.or.ddit.dummy.dto.DummyDto;

public class DummyDaoImpleCase3Test {
    DummyDao dao = new DummyDaoImpleCase3();

    @BeforeEach
    void setUp() {
        DummyDto dummy = new DummyDto();
        dummy.setCol1(2);
        dummy.setCol2("B");
        dao.insertDummy(dummy);
    }

    @Test
    @AfterEach
    void tearDown() {
        dao.deleteDummy(2);
    }

    @Test
    void testSelectDummy() {
        System.out.println(dao.selectDummy(2));
    }

    @Test
    void testSelectDummyList() {
        System.out.println(dao.selectDummyList());
    }
}
