<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>
        <h4>/05 요청으로부터 쿠키 복원</h4>

        <table>
            <thead>
                <tr>
                    <th>쿠키 이름</th>
                    <th>쿠키 값</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Cookie[] cookies = request.getCookies();
                    if(cookies != null) {
                        for(Cookie single : cookies){
                            String name = single.getName();
                            String value = URLDecoder.decode(single.getValue(), "UTF-8");
                            out.println("<tr><td>%s</td><td>%s</td></tr>".formatted(name, value));
                        }
                    }
                %>
            </tbody>
        </table>
    </body>
</html>