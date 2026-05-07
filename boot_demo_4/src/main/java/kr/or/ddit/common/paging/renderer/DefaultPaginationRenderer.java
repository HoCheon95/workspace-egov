package kr.or.ddit.common.paging.renderer;

import kr.or.ddit.common.paging.PaginationInfo;

public class DefaultPaginationRenderer implements PaginationRenderer {

    @Override
    public String renderPagination(PaginationInfo<?> paginationInfo, String fnName) {
        int startPage = paginationInfo.getStartPage();
        int endPage = paginationInfo.getEndPage();
        int totalPage = paginationInfo.getTotalPage();
        endPage = endPage > totalPage ? totalPage : endPage;
        int currentPage = paginationInfo.getCurrentPage();
        int blockSize = paginationInfo.getBlockSize();

        String pattern = "<a href='javascript:void(0)' data-page='%1$d' onclick='"+fnName+"(%1$d);'>%2$s</a>";

        StringBuilder sb = new StringBuilder();
        if (startPage > blockSize) {
            sb.append(String.format(pattern, startPage - blockSize, "이전"));
        }

        for (int i = startPage; i <= endPage; i++) {
            if (i == currentPage) {
                sb.append("[%d]".formatted(currentPage));
            } else {
                sb.append(String.format(pattern, i, i));
            }
        }


        if (endPage < totalPage) {
            sb.append(String.format(pattern, endPage + 1, "다음"));
        }



        return sb.toString();
    }

}
