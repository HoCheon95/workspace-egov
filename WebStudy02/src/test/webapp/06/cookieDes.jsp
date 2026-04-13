<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <h4>Cookie</h4>
        <pre>
            : Http 의 stateless 를 보완하기 위한 상태 저장 방식, 클라이언트 사이드에 저장되는 데이터 형태
            : Http 의 측면에서 쿠키는 request(cookie) 나 response(set-cookie) 의 헤더임

            쿠키 활용 단계
            1. 쿠키 생성 (서버사이드 Cookie) : name/value 필수 속성으로 생성
                value : 인코딩/디코딩에 대한 고려가 필요함

                domain(host) : 해당 도메인으로 발생하는 요청에 재전송 
                               생략시 쿠키의 출처가 반영됨
                               도메인 네임이 레벨 구조를 형성하고 있는 경우, 패턴 매칭에 따라 재전송 여부 결정
                               ex) www.naver.com(GTLD 구조 Global Tot Level Domain) www.naver.co.kr(NTLD 구조)
                               ex) .naver.com(네이버의 모든 서버로 재전송), www.naver.com(네이버의 www 서버로 재전송)
                path : 해당 path 의 하위 경로로 발생하는 요청을 재전송
                       생략시 쿠키 생성 경로가 반영됨
                max-age : 초단위의 만료 시한 생략시 세션과 생명주기가 동일해짐
                          ex) 0 : 삭제에 대한 표현, -1 : 브라우저 종료시 제거

                httpOnly : JS 로는 접근 불가 표현
                secure : https 요청에만 재전송됨
                samesite : [NONE, LAX, ASMESITE]
                ex) localhost:80, localhost:5173 => same site, other origin

            2. response 헤더(set-cookie, addCookie)로 전송

            3. 브라우저가 자기 저장소에 저장

            4. 다음번 요청의 헤더(cookie) 형태로 저장된 쿠키를 서버로 재전송
        </pre>
        <%
            Cookie sampleCookie = new Cookie("sampleCookie", "SampleValue");
            response.addCookie(sampleCookie);

            String koreanValue = URLEncoder.encode("한글 쿠키a", "UTF-8");
            Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);
            response.addCookie(koreanCookie);

            Cookie otherPathCookie = new Cookie("otherPathCookie", "otherPathCookie");
            otherPathCookie.setPath(request.getContextPath() + "/05");
            response.addCookie(otherPathCookie);

            Cookie longLiveCookie = new Cookie("longLiveCookie", "longLiveCookieValue");
            longLiveCookie.setPath(request.getContentType());
            longLiveCookie.setMaxAge(-1);
            response.addCookie(longLiveCookie);

            // Cookie otherDomainCookieValue = new Cookie("otherDomainCookieValue", "otherDomainCookieValue");
            // otherDomainCookieValue.setDomain("https://www.naver.com");
            // otherDomainCookieValue.setPath("/");
            // response.addCookie(otherDomainCookieValue);
        %>
        <a href="cookieRead.jsp">/06 에서 쿠키 읽기</a>
        <a href="/05/cookieRead.jsp">/05 에서 쿠키 읽기</a>
    </body>
</html>
