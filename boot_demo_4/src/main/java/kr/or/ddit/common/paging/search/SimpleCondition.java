package kr.or.ddit.common.paging.search;

import java.io.Serializable;
import lombok.Data;

/**
 * 단순 키워드 검색에 사용
 */
@Data
public class SimpleCondition implements Serializable {
    private String searchType;
    private String searchWord;
}
