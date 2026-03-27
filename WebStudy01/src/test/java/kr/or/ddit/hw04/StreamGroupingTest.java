package kr.or.ddit.hw04;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kr.or.ddit.hw04.domain.Unit;
import kr.or.ddit.hw04.domain.UnitType;

public class StreamGroupingTest {

    @Test
    void test() {
        Map<UnitType, List<Unit>> dummy = Arrays.stream(Unit.values())
                .collect(Collectors.groupingBy(u -> {
                    System.out.println(u);
                    return u.getType();
                }));

        System.out.println(dummy);
    }
}
