package kr.or.ddit.common.paging.renderer;

import kr.or.ddit.common.paging.PaginationInfo;

public interface PaginationRenderer {
    /**
     *  페이징 링크를 동적으로 생성.
     * 
     * @param paginationInfo : startPage, endPage, totalPage 등을 가진 객체
     * @param fnName : 
     * @return
     */
    String renderPagination(PaginationInfo<?> paginationInfo, String fnName);

    default String renderPagination(PaginationInfo<?> paginationInfo) {
        return renderPagination(paginationInfo, null);
    }
}
