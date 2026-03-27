<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>환율 계산기</title>
</head>
<body>
    <h1>환율 계산기</h1>
    <form action="${pageContext.request.contextPath}/hw05/exchange" method="post">
        <label>금액: <input type="text" name="amount" required></label><br>
        <label>From:
            <select name="from" required>
                <c:forEach items="${currencyList}" var="currency">
                    <option value="${currency.currencyCode}">${currency.currencyCode}</option>
                </c:forEach>
            </select>
        </label><br>
        <label>To:
            <select name="to" required>
                <c:forEach items="${currencyList}" var="currency">
                    <option value="${currency.currencyCode}">${currency.currencyCode}</option>
                </c:forEach>
            </select>
        </label><br>
        <button type="submit">환전</button>
    </form>

    <c:if test="${not empty error}">
        <p style="color:red">오류: ${error}</p>
    </c:if>

    <c:if test="${not empty exchangeResponse}">
        <h2>결과</h2>
        <p>${exchangeResponse.amount} ${exchangeResponse.from.currencyCode} =&gt; ${exchangeResponse.formattedResult}</p>
        <p>환율: ${exchangeResponse.rate}</p>
    </c:if>
</body>
</html>