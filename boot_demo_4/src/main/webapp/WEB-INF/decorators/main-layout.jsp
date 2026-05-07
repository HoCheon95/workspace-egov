<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><sitemesh:write property="title"/></title>
    <%@ include file="/WEB-INF/fragments/preCss.jsp" %>
    
<sitemesh:write property="head"/>
</head>
<body>
    <div class="mainBody">
    
      <sitemesh:write property="body"/>
      
    </div>
<c:if test="${not empty errors}"> 
    <script>
        alert("${errors}");
    </script>
    <c:remove var="errors" scope="session" />
</c:if>
<%@ include file="/WEB-INF/fragments/postScript.jsp" %>
<p style="position: absolute; bottom: 0;">sitemesh</p>
</body>
</html>