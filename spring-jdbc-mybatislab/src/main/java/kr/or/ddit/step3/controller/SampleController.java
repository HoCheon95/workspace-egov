package kr.or.ddit.step3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.annotation.PostConstruct;
import kr.or.ddit.step3.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SampleController {
    private SampleService service;
    
    @Autowired
    public void setService(SampleService service) {
        this.service = service;
        log.info("setter injection 으로 {} 주입됨", service);
    }

    @PostConstruct
    public void init() {
        log.info("{} 에서 {} 을 주입받았음.", this, service);
    }

    public void receiveCommand(String key) {
        String model = service.readData(key);
        log.info("model : {}", model);
    }
}
