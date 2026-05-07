package kr.or.ddit.step3.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import kr.or.ddit.step3.dao.SampleDao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SampleService {
    private SampleDao dao;
    
    public SampleService(SampleDao dao){
        super();
        this.dao = dao;
    }

    @PostConstruct
    public void init() {
        log.info("{} 에서 {} 을 주입받았음.", this, dao);
    }

    public String readData(String key){
        return dao.selectData(key) + "가공한 info";
    }
}
