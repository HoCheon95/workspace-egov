<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // response.setContentType("text/html;charset=UTF-8");
    // response.setContentLengthLong(20);
    // response.setHeader("Content-Disposition", "attachment;filename=dummy bak.html");
    // response.setHeader("Cache-Control", "private, max-age=3600");
    // response.setIntHeader("Refresh", 1);
    response.sendRedirect("Location", request.getContextPath() + "/03/echo");

%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h1><%=System.currentTimeMillis()%></h1>
    <a href="">RELOAD</a>
    <h4>Http Response packaging -> HttpServletResponse</h4>
    <pre>
        1. Response Line : Status Code : 3자리의 숫자코드의 요청 처리 상태 여부를 표현.

            1xx : ING (연결이 계속 유지중...)
            2xx : OK
            3xx : body 가 없는 응답의 형태.클라이언트느 응답 수신 후 추가 액션을 해야함.
                304 (Not Modified) : 로컬 캐시 데이터 사용 여부 결정
                301/302/307 (Moved) : 자원의 새로운 위치로 재 요청 전송(Location 헤더와 함꼐 사용됨.)

            4xx : Client Side Failure
                404 (Not Found)
                405 (Method Not Allowed)
                400 (Bad Request) : 요청을 검증하는 과정에서 활용됨. ex) 필수 데이터 누락, 데이터 길이 오류, 데이터 포맷 오류 등 ...

                406 (Not Acceptable) : 현재 요청에서 요구하는 컨텐츠 타입(accept)을 생성할 수 없음
                415 (UnSupported Media Type) : 현재 요청의 본문 형식(content-type)을 읽을 수 없음

                401 (UNAUTHORIZED) : 보호자원에 대한 요청이 발생한 경우 인증(authentication)이 필요함을 표현
                403 (FORBIDDEN) : 보호자원에 대한 접근 권한을 소유(authorization)하지 못했음을 표현
            5xx : Server Side Failure, 500(Internal Server Error)


        2. Response Header : meta data 영역 (response body 에 대한 부가적인 데이터)

            1) Content-* : Content-Type(응답본문의 MIME), Content-Length(응답 본문의 길이)
                Content-Disposition
                    inline(기본값) : 응답의 본문을 브라우저 창 내에서 소비함
                    attachment;filename... : 응답 본문을 별개 파일로 저장함

            2) Cache-Control : 캐시 정책 제어에 사용
                    no-store/no-cache : 캐싱하지 않도록 설정
                    private/public, max-age : 캐싱하도록 설정하고 만료 시한을 초단위로 결정
            
            3) Refresh : 서버로부터 수신한 자원을 징정 주기로 리로딩함(동기 요청으로 수신한 응답에 대해서만 동작함).

            4) Location : 30x 상태코드로 요청을 재지정하거나 PRG 패턴을 구현할때 활용됨

            5) CORS : Access-Control-*** 형태의 헤더


        3. Response Body(content body, message body) : 응답 본문
            -> response.getWriter(), response.getOutputStream()
        <button id="fetch-btn">FETCH</button>
    </pre>
    <div id="result"></div>
    <script>
        document.getElementById("fetch-btn").addEventListener("click", async () => {
            setInterval(async () => {
                const resp = await fetch("fetchServerTime.jsp");
                const text = await resp.text();
                result.innerHTML = text;
            }, 1000);
        });
    </script>
</body>
</html>