<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>
        <h4>세션 (HttpSession)</h4>
        <pre>
            : stateless 를 statefull 로 바꿀 수 있는 서버사이드 저장 메카니즘

            세션
            connectfull (DB) : 세션과 connection 이 동일한 의미를 가짐
            connectless (WEB) : 하나의 어플리케이션을 한 사용자가 사용하기 시작한 시점부터 사용 종료 이벤트를 발생시켰을떄까지의 기간으로 정의

            생명주기
                시작 : 최초의 요청이 발생하면 컨테이너는 한 세션을 생성함 -> session ID 를 부여함
                       -> response 에 session id 를 포함시켜서 클라이언트로 전송함
                       -> 다음번 요청이 발생하면, 요청에 session ID 가 포함된 상태로 서버에 재전송됨 (세션 트래킹 방법)
                세션 아이디를 요청에 포함시키는 방법(세션 트래킹 모드)
                1. Cookie : request 의 cookie 헤더를 통해 세션 아이디 재전송
                2. URL : request line, URL 에 세션 파라미터로 세션 아이디 재전송
                3. SSL (Secure Socket Layer) : 암호화된 채널을 통해 세션 아이디 재전송
                종료
                1. 로그아웃 : session.invalidate()

                -> 쓰레기 세션이 발생할 수 있음 timeout 이내에 새로운 요청이 발생하지 않으면 세션이 만료됨
                2. 트래킹 모드로 사용될 세션 아이디가 삭제됐을때
                3. 브라우저 완전 종료

            세션이 왜 필요할까?
                세션과 세션 트래킹 모드를 통해 stateless 로 연관성이 없는 http 요청들을 하나의 클라이언트와 연관지어 그룹으로 표현할 수 있게됨
			세션 아이디 : <%=session.getId() %>
			세션 생성 시점 : <%=new Date(session.getCreationTime()) %>
			세션내 마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
			세션 timeout : <%=session.getMaxInactiveInterval() %>
            <%
               session.setMaxInactiveInterval(4*60);
            %>
            세션 timeout (수정 이후) : <%=session.getMaxInactiveInterval() %>
        </pre>
    </body>
</html>