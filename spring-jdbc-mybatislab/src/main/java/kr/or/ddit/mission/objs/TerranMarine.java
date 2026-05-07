package kr.or.ddit.mission.objs;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope("prototype")
@RequiredArgsConstructor
@ToString
public class TerranMarine {
    private final RifleGun gun;

    @PostConstruct
    public void init() {
        log.info("주입이 완료된 객체 : {}", gun);
    }
}
