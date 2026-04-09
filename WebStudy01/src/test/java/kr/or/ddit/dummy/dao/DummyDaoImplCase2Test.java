package kr.or.ddit.dummy.dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.dummy.dto.DummyDto;

public class DummyDaoImplCase2Test {

    DummyDaoImplCase2 dao = new DummyDaoImplCase2();

    @Test
    void testQueryTemplate() {
        List<DummyDto> list =dao.queryTemplate();
        System.out.println(list);
    }
}
