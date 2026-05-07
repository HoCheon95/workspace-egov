<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <pre>
        파일 업로드 UI
        1. method = "post"
        2. enctype = "multipart/form-data"
        ==> multipart content 를 전송하는 body 가 구성됨
    </pre>
    <form:form modelAttribute="uploadDto" method="post" enctype="multipart/form-data">
        <input type="text" name="uploader"  value="${uploadDto.uploader }">
        <form:errors path="uploader" />
        <input type="file" name="uploadFile" >
        <form:errors path="uploadFile" />
        <button type="submit">업로드</button>
    </form:form>

    <c:if test="${not empty uploader }">
        <div>
            업로더 : ${uploader }
            파일경로 : ${filePath }
        </div>
    </c:if>
</body>
</html>