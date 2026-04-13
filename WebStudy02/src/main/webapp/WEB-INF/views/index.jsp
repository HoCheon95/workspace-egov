<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>

    <span>web study 2</span>
        <h4>웰컴패키지</h4>
        <a href="?Language=ko-KR">한국어</a>
        <a href="?Language=en-US">영어</a>
        <fmt:setLocale value="en-US"/>
        <fmt:setBundle basename="kr.or.ddit.Messages"/>
        <h1><fmt:message key="hi"/></h1>
        <%
            request.getUserPrincipal();
        %>
        <c:if test="${empty pageContext.request.userPrincipal }">
            <a href="<c:url value='/login'/>">로그인</a>
        </c:if>
        <c:if test="${not empty pageContext.request.userPrincipal }">
            ${pageContext.request.userPrincipal.name } <a href="<c:url value='/logout'/>">로그아웃</a>
        </c:if>
        <ul>

            <li>
                <a href="${pageContext.request.contextPath }/hw02/worldtime">공용 세계시간</a>
            </li>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') }">
                <li>
                    <a href='<c:url value="/hw05/exchange"/>'>관리자용 환전서비스</a>
                </li>
                <li>
                    <a href="<c:url value='/admin/member-list'/>">관리자용 회원 목록</a>
                </li>
            </c:if>
            <c:if test="${not empty pageContext.request.userPrincipal }">
                <li>
                    <a href='<c:url value="/hw04/convert"/>'>회원용 단위변환서비스</a>
                </li>
            </c:if>
        </ul>
    </body>
</html>