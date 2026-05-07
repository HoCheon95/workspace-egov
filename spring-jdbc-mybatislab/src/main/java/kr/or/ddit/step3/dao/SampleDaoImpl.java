package kr.or.ddit.step3.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SampleDaoImpl implements SampleDao{

    @Override
    public String selectData(String key) {
        return "%s 를 받아서 조회한 raw data".formatted(key);
    }
}
