<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>
        <h4>요청 본문을 구성하는 방법</h4>
        <form method="get" action="<%=request.getContextPath()%>/03/request-data">
            <input type="text" name="name" placeholder="이름">
            <input type="number" name="age" placeholder="나이">
            <button type="submit">요청 본문이 없는 GET(query string 형태 전송)</button>
        </form>

        <form method="post" action="<%=request.getContextPath()%>/03/request-data" enctype="application/x-www-form-urlencoded">
            <input type="text" name="name" placeholder="이름">
            <input type="text" name="name" placeholder="이름">
            <input type="number" name="age" placeholder="나이">
            <button type="submit">요청 본문이 있는 POST 요청</button>
        </form>

        <form method="post" action="<%=request.getContextPath()%>/03/request-data?name=뫄뫄뫄" enctype="application/x-www-form-urlencoded">
            <input type="text" name="name" placeholder="이름">
            <input type="number" name="age" placeholder="나이">
            <button type="submit">요청 본문+쿼리스트링이 있는 POST 요청</button>
        </form>

        <form id="json-form" method="post" action="<%=request.getContextPath()%>/03/request-data">
            <input type="text" name="name" placeholder="이름">
            <input type="number" name="age" placeholder="나이">
            <button type="submit">요청 본문으로 JSON 페이로드를 비동기 요청으로 전송.</button>
        </form>
        <script type="text/javascript">
            const jsonForm = document.getElementById("json-form");
            jsonForm.addEventListener("submit", (e) => {
                e.preventDefault();
                // form 의 동기요청을 비동기로 전환
                // 요청이 발생하는 방식은 비동기로 전환되지만, 나머지 모든 조건의 기존의 form 과 동일.
                const form = e.target;
                const fd = new FormData(form);
                const url = form.action;
                const method = form.method;
                const target = {
                    name: fd.get("name"),
                    age: fd.get("age")
                };
                const body =JSON.stringify(target);
                fetch(url, {
                    method: method,
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: body
                });
            });
        </script>
    </body>
</html>

