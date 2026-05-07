package kr.or.ddit.mission.objs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.ToString;

@Repository
@ToString
public class Barrack {
    @Autowired
    private ConfigurableApplicationContext context;

    @Getter
    private List<TerranMarine> marines = new ArrayList<>();

    public TerranMarine generateMarine() {
        TerranMarine marine = context.getBean(TerranMarine.class);
        marines.add(marine);
        return marine;
    }
}
