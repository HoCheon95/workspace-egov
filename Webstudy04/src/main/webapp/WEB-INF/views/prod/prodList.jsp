<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 리스트</title>
</head>
<body>
    <%-- 상품명, 상품분류명, 제조사명, 구매가, 판매가, 세일가, 마일리지 --%>
    <a href="<c:url value='/prod/create'/>">신규등록</a>
    <table class="table">
        <thead>
            <th>상품명</th>
            <th>상품분류명</th>
            <th>제조사명</th>
            <th>구매가</th>
            <th>판매가</th>
            <th>세일가</th>
            <th>마일리지</th>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty prodList}">
                    <c:forEach items="${prodList}" var="prod">
                        <c:url value="/prod/detail" var="detailUrl">
                            <c:param name="what" value="${prod.prodId}"/>
                            <c:param name="p2" value="v2"/>
                        </c:url>
                        <tr>
                            <td><a href="${detailUrl}">${prod.prodName}</a></td>
                            <td>${prod.lprod.lprodName}</td>
                            <td>${prod.buyer.buyerName}</td>
                            <td>${prod.prodCost}</td>
                            <td>${prod.prodPrice}</td>
                            <td>${prod.prodSale}</td>
                            <td>${prod.prodMileage}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7">데이터 없음.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>