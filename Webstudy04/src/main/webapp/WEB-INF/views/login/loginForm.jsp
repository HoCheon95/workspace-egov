<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
        <c:if test="${not empty SECURITY_LAST_EXCEPTION}">
            <script type="text/javascript">
                alert("${SECURITY_LAST_EXCEPTION.message}");
            </script>
            <c:remove var="SECURITY_LAST_EXCEPTION" scope="session"/>
        </c:if>
    </head>
    <body>
        <form method="post" enctype="application/x-www-form-urlencoded">
            <input type="text" name="username" placeholder="아이디" required/>
            <input type="password" name="password" placeholder="비밀번호" required/>
            <button type="submit">로그인</button>
        </form>
    </body>
</html>