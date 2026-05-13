<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<a href="/">Home</a> |
<security:authorize access="isAnonymous()">
    <a href="/login">로그인</a> | 
</security:authorize>

<security:authorize access="isAuthenticated()">
    <security:authentication property="principal" var="principal"/>
    <security:authentication property="authorities" var="authorities"/>
    <a href="/member/mypage">${principal.realUser.memName}</a>, ${authorities} |
    <a href="/logout">로그아웃</a> | 
    <a href="http://localhost:5174/" target="_blank">리액트 페이지로 이동</a>
</security:authorize>
<ul>
    <security:authorize access="hasRole('ADMIN')">
        <li><a href="/admin/memberList">Member List</a></li>
        <li><a href="/buyer/list">Buyer List</a></li>
    </security:authorize>
    <li><a href="/prod/list">Product List</a></li>
</ul>