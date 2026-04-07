<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>도착지 B</h4>
    <h1>B에서 만든 페이지의 일부분</h1>
    <pre>
    	page scope : ${pageContextScope.message }
    	request scope : ${requestScope.message }
    	session scope : ${sessionScope.message }
    	application scope : ${applicationScope["message"] }
    </pre>
</body>
</html>