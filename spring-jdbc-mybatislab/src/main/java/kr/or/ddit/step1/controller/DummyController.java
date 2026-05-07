package kr.or.ddit.step1.controller;

import kr.or.ddit.step1.service.DummyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyController {
    
    private DummyService service;
    
    public DummyController(DummyService service) {
        super();
        this.service = service;
    }

    public void receiveCommand() {
        String info = service.readDummy();
        log.info("{}", info);
    }
}
