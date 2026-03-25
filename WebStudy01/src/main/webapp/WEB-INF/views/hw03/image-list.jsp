<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>
        <form action="/hw02/image" method="get" onsubmit="return false;">
            <select name="imageName" onchange="this.form.submit();">
                <c:forEach items="${imageFiles}" var="single">
                    <option>${single}</option>
                </c:forEach>
            </select>
        </form>
        <select id="dummySel">
        
        </select>
        <script>
            fetch("", {
                method:"get",
                headers:{
                    "accept":"application/json",
                    "accept-language":"ko-KR"
                }
            }).then((resp)=>resp.json())
            .then((imageFiles)=>{
                dummySel.innerHTML = imageFiles.map(imageName =>`<option>\${imageName}</option>`).join("\n");
            });
        </script>
    </body>
</html>