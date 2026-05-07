<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="uploader">
            <input type="file" name="uploadFile">
            <button type="submit">업로드</button>
        </form>
    </body>
</html>