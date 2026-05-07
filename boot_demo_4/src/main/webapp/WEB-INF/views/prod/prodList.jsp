<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>상품 목록 - Product List</title>
        <style>
            /* 추가적인 커스텀 스타일링 */
            body { background-color: #f8f9fa; }
            .container { background-color: #ffffff; border-radius: 15px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
            .table thead { background-color: #0d6efd; color: white; }
            .price-text { font-family: 'Consolas', monospace; text-align: right; }
        </style>
    </head>
    <body>

        <div class="container my-5 p-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="fw-bold text-primary">📦 상품 목록</h2>
                <span class="badge bg-secondary">총 ${prodList.size()} 건</span>
            </div>
            <div>
                <a href="/prod/create">신규 등록</a>
            </div>

            <div class="table-responsive">
                <table class="table table-hover table-striped align-middle">
                    <thead>
                        <tr>
                            <th scope="col" class="text-center">번호</th>
                            <th scope="col" class="text-center">상품명</th>
                            <th scope="col">분류</th>
                            <th scope="col">제조사</th>
                            <th scope="col" class="text-end">구매가</th>
                            <th scope="col" class="text-end">판매가</th>
                            <th scope="col" class="text-end text-danger">세일가</th>
                            <th scope="col" class="text-end">마일리지</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>
                            <c:when test="${not empty prodList}">
                                <c:forEach var="prod" items="${prodList}">
                                    <tr>
                                        <td class="text-center">${prod.rnum}</td>

                                        <td class="fw-semibold">
                                            <a
                                            target="_blank"
                                            href="/prod/detail?id=${prod.prodId}" class="text-decoration-none text-dark">
                                            ${prod.prodName}</a>
                                        </td>
                                        <td><span class="badge border text-dark fw-normal">${prod.lprod.lprodName}</span></td>
                                        <td>${prod.buyer.buyerName}</td>
                                        <td class="price-text"><fmt:formatNumber value="${prod.prodCost}" pattern="#,###"/>원</td>
                                        <td class="price-text fw-bold"><fmt:formatNumber value="${prod.prodPrice}" pattern="#,###"/>원</td>
                                        <td class="price-text text-danger fw-bold"><fmt:formatNumber value="${prod.prodSale}" pattern="#,###"/>원</td>
                                        <td class="price-text text-success">${prod.prodMileage} P</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="8" class="text-center py-5 text-muted">등록된 상품 정보가 없습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                    <tfoot>
                        <tr>


                            <td colspan="8" style="text-align: center; gap: 10px; display: flex; justify-content: center; align-items: center;">
                                <div class="paging-area" data-pg-target="#searchForm">
                                    ${pagingHtml}
                                </div>
                            </td>
                            <div data-pg-form="#searchForm">
                                <select name="lprodGu">
                                    <option value="">분류 전체</option>
                                    <c:forEach var="lprod" items="${lprodList}">
                                        <option value="${lprod.lprodGu}" ${detailSearch.lprodGu eq lprod.lprodGu ? 'selected' : ''}>${lprod.lprodName}</option>
                                    </c:forEach>
                                </select>
                                <select name="buyerId">
                                    <option value="">제조사 전체</option>
                                    <c:forEach var="buyer" items="${buyerList}">
                                        <option value="${buyer.buyerId}" ${detailSearch.buyerId eq buyer.buyerId ? 'selected' : ''}>${buyer.buyerName}</option>
                                    </c:forEach>
                                </select>
                                <input type="text" name="prodName" placeholder="검색어를 입력하세요" value="${detailSearch.prodName}"/>
                                <button type="button" class="searchBtn">검색</button>
                            </div>
                        </tr>
                    </tfoot>

                </table>
                <form:form method="get" id="searchForm" modelAttribute="detailSearch" >
                    <form:input path="lprodGu" readonly="true"/>
                    <form:input path="buyerId" readonly="true"/>
                    <form:input path="prodName" readonly="true"/>
                    <input type="text" readonly name="page">
                </form:form>

                
                <script type="text/javascript" src="/js/app/commons/paging.js"></script>
            </div>
        </div>
    </body>
</html>