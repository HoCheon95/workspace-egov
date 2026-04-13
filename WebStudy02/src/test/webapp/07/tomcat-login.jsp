<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <form method="post" action="j_security_check">
        <input type="text" name="j_username" placeholder="아이디"/>
        <input type="text" name="j_password" placeholder="비밀번호"/>
        <button type="submit">로그인</button>
    </form>
</body>
</html>