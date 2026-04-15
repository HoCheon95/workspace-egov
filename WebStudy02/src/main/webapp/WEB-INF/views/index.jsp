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

    <span>Web Study 2</span>
        <h4>웰컴패키지</h4>
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
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') }">
                <li>
                    <a href="<c:url value='/admin/member-list'/>">관리자용 회원 목록</a>
                </li>
            </c:if>
            <c:if test="${not empty pageContext.request.userPrincipal }">
                <li>
                    <a href="<c:url value='/member/change-password'/>">비밀번호 변경</a>
                </li> 
                <li>
                    <a href="<c:url value='/member/mypage'/>">마이페이지</a>
                </li> 
            </c:if>
        </ul>
    </body>
</html>