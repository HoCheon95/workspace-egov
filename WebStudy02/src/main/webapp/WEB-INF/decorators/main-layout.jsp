<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title><sitemesh:write property="title"/></title>

        <%@ include file="/WEB-INF/fragments/preCss.jsp"%>
        <sitemesh:write property="head"/>

    </head>
    <body>
        <h4>공통 레이아웃의 제목</h4>
        <div class="mainBody">

            <sitemesh:write property="body"/>

        </div>
        <%@ include file="/WEB-INF/fragments/postScript.jsp"%>
    </body>
</html>