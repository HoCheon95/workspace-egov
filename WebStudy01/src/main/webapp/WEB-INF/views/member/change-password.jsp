<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
        <c:if test="${not empty message}">
            <script type="text/javascript">
                alert("${message}");
            </script>
            <c:remove var="message" scope="session"/>
        </c:if>
    </head>
    <body>
        <h4>비밀번호 변경</h4>
        <form method="post"" id="pw-form">
            <ul>
                <li>
                    현재 비밀번호 : <input type="password" name="oldPassword" required />
                </li>
                <li>
                    새 비밀번호 : <input type="password" name="newPassword" required />
                </li>
                <li>
                    비밀번호 확인 : <input type="password" name="retypePassword" required />
                </li>
                <li>
                    <button type="submit">전송</button>
                    <button type="reset">취소</button>
                </li>
            </ul>
        </form>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/member/change-password.js"></script>
    </body>
</html>