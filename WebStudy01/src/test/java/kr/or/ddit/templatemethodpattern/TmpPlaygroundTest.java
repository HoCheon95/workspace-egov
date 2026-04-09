package kr.or.ddit.templatemethodpattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

/**
 * ddit 학생들의 행동 분석
 * 1 단계 : 지문 인체킹
 * 2 단계 : 강의실 내에서의 행동 케이스별로 ...
 * 3 단계 : 지문 아웃체킹
 * 
 * abstract DDITStudent{
 *  final 1, 2, 3 단계를 고정.
 *  abstract 2단계는 추상화되어있어야함.
 * }
 * 
 * GoodStudent extends DDITStudent{
 *  2단계 구체화
 * }
 * BadStudent extends DDITStudent{
 *  2단계 구체화
 * }
 */
public class TmpPlaygroundTest {

    @Test
    void test() {
        DDITStudent[] ddits = new DDITStudent[]{
            new GoodStudent(),
            new LazyStudent()
        };
        for (DDITStudent single : ddits){
            single.template();
        }
    }
}
