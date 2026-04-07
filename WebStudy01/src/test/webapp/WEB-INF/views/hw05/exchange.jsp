<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>환율 계산기</title>
</head>
<body>
<c:if test="${not empty resultDto}">
	<h1>변환 결과 : ${resultDto}</h1>
</c:if>
    <h1>환율 계산기</h1>
    <form method="post" enctype="application/x-www-form-urlencoded">
        <label>금액: <input type="text" name="amount" required></label><br>
        <label>From:
            <select name="from" required>
                <c:forEach items="${currencies}" var="currency">
                    <option value="${currency.currencyCode}">${currency.currencyCode}</option>
                </c:forEach>
            </select>
        </label><br>
        <label>To:
            <select name="to" required>
                <c:forEach items="${currencies}" var="currency">
                    <option value="${currency.currencyCode}">${currency.currencyCode}</option>
                </c:forEach>
            </select>
        </label><br>
        <button type="submit">환전</button>
    </form>

</body>
</html>