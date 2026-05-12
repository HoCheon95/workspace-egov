
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.authority.SimpleGrantedAuthority" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>웰컴페이지</h4>
    사용자로부터 요청 http://localhost 로 발생함.
    <h4>현재 사용자의 로케일 : ${clientLocale }</h4>
    <security:authorize access="isAuthenticated()">
        <a href="/logout">로그아웃</a>
        <h4>현재 사용자의 인증 정보 : ${pageContext.request.userPrincipal }</h4>
        <h4>현재 사용자의 인증 정보 (principal) : <security:authentication property="principal" var="principal"/>${principal}</h4>
        <h4>현재 사용자의 인증 정보 (details): <security:authentication property="details"/></h4>
        <h4>현재 사용자의 인증 정보 (authorities): <security:authentication property="authorities"/></h4>
        <h4>현재 사용자의 이름 : ${principal.realUser.memName}</h4>
    ${pageContext.request.userPrincipal.authorities.contains("ROLE_ADMIN") }
    <%=((Authentication) request.getUserPrincipal()).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))%>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <a href="/login">로그인</a>
    </security:authorize>
    <ul>
        <security:authorize access="hasAnyRole('ADMIN')">
            <li>관리자만 접속할 수 있는 보호 자원 링크</li>
        </security:authorize>
    </ul>
</body>
</html>