package kr.or.ddit.common.paging;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.common.paging.search.SimpleCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * 페이징 처리에 필요한 프로퍼티를 가진 객체
 */

@Getter
public class PaginationInfo<T> implements Serializable {
    private final int screenSize; // 고정
    private final int blockSize; // 고정

    @Setter
    private SimpleCondition simpleCondition; // 단순 키워드 검색
    @Setter
    private T detailCondition; // 상세 검색
    @Setter
    private Map<String, Object> variousCondition;
    public void addSingleCondition(String searchType, Object searchValue) {
        variousCondition.put(searchType, searchValue);
    }
    public void addAll(Map<String, Object> conditions){
        variousCondition.putAll(conditions);
    }

    @Setter
    private int totalRecord; // DB 조회

    @Setter
    private int currentPage; // 사용자에 의해 결정


    // private int totalPage; // 연산
    // private int startRow; // 연산
    // private int endRow; // 연산
    // private int startPage; // 연산
    // private int endPage; // 연산

    // private int offset;

    private int rnum;

    public int getOffset() {
        return (currentPage - 1) * screenSize;
    }


    @Setter
    private List<T> dataList;

    public PaginationInfo() {
        this(10, 5);
    }

    public PaginationInfo(int screenSize, int blockSize) {
        this.screenSize = screenSize;
        this.blockSize = blockSize;
        this.variousCondition = new LinkedHashMap<>();
    }


    public int getTotalPage() {
        return (totalRecord + screenSize - 1) / screenSize;
    }

    public int getStartRow() {
        return getEndRow() - (screenSize - 1);
    }

    public int getEndRow() {
        return currentPage * screenSize;
    }

    public int getStartPage() {
        return getEndPage() - (blockSize - 1);
    }

    public int getEndPage() {
        return ((currentPage + (blockSize - 1)) / blockSize) * blockSize;
    }

}
