<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>제조사 목록</title>
</head>
<body>
    <table class="table">
        <thead>
            <tr>
                <th>제조사명</th>
                <th>제조사 분류코드</th>
                <th>소재지</th>
                <th>담당자명</th>
                <th>이메일</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty buyerList}">
                    <c:forEach items="${buyerList}" var="buyer">
                        <c:url value="/buyer/detail" var="detailUrl">
                            <c:param name="what" value="${buyer.buyerId}"/>
                            <c:param name="p2" value="v2"/>
                        </c:url>
                        <tr>
                            <td><a href="${detailUrl}">${buyer.buyerName}</a></td>
                            <td>${buyer.lprod.lprodGu}</td>
                            <td>${buyer.buyerAdd1}</td>
                            <td>${buyer.buyerCharger}</td>
                            <td>${buyer.buyerMail}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5">데이터 없음.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>