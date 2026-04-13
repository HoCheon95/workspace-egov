<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>World Time Service</title>
    </head>
    <body>
        <h1>세계 시간 서비스(SSR)</h1>
        <form method="GET" action="${pageContext.request.contextPath}/hw02/worldtime">
            <label for="locale">로케일:</label>
            <select name="locale">
                <%
                    Map<String, String> localeMap = (Map<String, String>) request.getAttribute("localeMap");
                    if (localeMap != null) {
                        for (Map.Entry<String, String> entry : localeMap.entrySet()) {
                            out.println("<option value='" + entry.getKey() + "'>" + entry.getValue() + "</option>");
                        }
                    }
                %>
            </select>
            <br><br>
            <label for="timezone">타임존:</label>
            <select name="timezone">
                <%
                    Map<String, String> zoneMap = (Map<String, String>) request.getAttribute("zoneMap");
                    if (zoneMap != null) {
                        for (Map.Entry<String, String> entry : zoneMap.entrySet()) {
                            out.println("<option value='" + entry.getKey() + "'>" + entry.getValue() + "</option>");
                        }
                    }
                %>
            </select>
            <br><br>
            <button type="submit">시간 확인(Sync+SSR)</button>
        </form>
        <h1>세계 시간 서비스(CSR)</h1>
        <form method="GET" action="${pageContext.request.contextPath}/hw02/worldtime" id="async-form">
            <label for="locale">로케일:</label>
            <select name="locale" id="locale">
                
            </select>
            <br><br>
            <label for="timezone">타임존:</label>
            <select name="timezone" id="timezone">
                
            </select>
            <br><br>
            <button type="submit">시간 확인(aSync+CSR)</button>
        </form>
        <div id="result"></div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/hw03/world-time-cn.js"></script>
    </body>
</html>

