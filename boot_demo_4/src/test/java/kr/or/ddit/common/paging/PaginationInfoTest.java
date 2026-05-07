package kr.or.ddit.common.paging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaginationInfoTest {

    PaginationInfo<?> paginationInfo = new PaginationInfo<>(10, 5);

    @Test
    void testGetTotalPage() {
        int totalRecord = 100;
        paginationInfo.setTotalRecord(totalRecord);
        assertEquals(10, paginationInfo.getTotalPage());

        totalRecord = 103;
        paginationInfo.setTotalRecord(totalRecord);
        assertEquals(11, paginationInfo.getTotalPage());

        totalRecord = 110;
        paginationInfo.setTotalRecord(totalRecord);
        assertEquals(11, paginationInfo.getTotalPage());
    }

    @Test
    void testGetEndRow() {
        int currentPage = 1;
        paginationInfo.setCurrentPage(currentPage);

        assertEquals(10, paginationInfo.getEndRow());



        currentPage = 3;
        paginationInfo.setCurrentPage(currentPage);
        assertEquals(30, paginationInfo.getEndRow());

        currentPage = 5;
        paginationInfo.setCurrentPage(currentPage);
        assertEquals(50, paginationInfo.getEndRow());

    }


    @Test
    void testGetEndPage() {
        int currentPage = 1;
        paginationInfo.setCurrentPage(currentPage);
        assertEquals(5, paginationInfo.getEndPage());

        currentPage = 3;
        paginationInfo.setCurrentPage(currentPage);
        assertEquals(5, paginationInfo.getEndPage());

        currentPage = 5;
        paginationInfo.setCurrentPage(currentPage);
        assertEquals(5, paginationInfo.getEndPage());

        currentPage = 9;
        paginationInfo.setCurrentPage(currentPage);
        assertEquals(10, paginationInfo.getEndPage());
    }


}
