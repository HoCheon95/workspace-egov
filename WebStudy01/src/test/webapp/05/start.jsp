<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>
        <h4>출발지 (A)</h4>
        <%
            // 4개 scope에 같은 키("message")로 다른 값을 설정
            pageContext.setAttribute("message", "PAGE 에 저장된 메시지");
            request.setAttribute("message", "REQUEST 에 저장된 메시지");
            session.setAttribute("message", "SESSION 에 저장된 메시지");
            application.setAttribute("message", "APPLICATION 에 저장된 메시지");
            
            // 1. forward: 제어권을 완전히 넘겨서 응답 전체를 해당 자원이 생성하게 한다
            String path = "/05/dest.jsp";
            // RequestDispatcher rd = request.getRequestDispatcher(path);
            // rd.forward(request, response); // 응답을 전체로 전송
            
            
            // 2. include: 다른 자원의 실행 결과를 현재 응답의 일부로 포함시킨다
            // rd.include(request, response); // 응답을 일부로 전송
            
            // 3. redirect
            String location = request.getContextPath() + path;
            response.sendRedirect(location);
        %>
        <h1>A 에서 만든 꼬릿말</h1>
    </body>
</html>