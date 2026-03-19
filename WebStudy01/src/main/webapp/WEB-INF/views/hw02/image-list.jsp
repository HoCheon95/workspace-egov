<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>image list</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/hw02/image">
    <select name="imageName" id="image">
        <option value="">이미지 선택(prompt text)</option>
        <%
            // 🔴 [SSR 방식] 서블릿에서 데이터를 넘겨주었는지 확인한다.
            List<String> fileList = (List<String>) request.getAttribute("fileName");
            if (fileList != null) {
                for (String name : fileList) {
        %>
            <option value="<%= name %>"><%= name %></option>
        <%
                }
            }
        %>
        </select>
    <button type="submit">전송</button>
</form>

<script>
    // 🔴 [CSR 방식] select 태그 안의 option 개수를 확인한다.
    const selectBox = document.querySelector("#image");

    // 🔴 option 개수가 1개 이하라면 (즉, SSR로 데이터가 안 그려졌다면) CSR을 실행한다.
    if (selectBox.options.length <= 1) {
        fetch('${pageContext.request.contextPath}/hw02/image-list-json')
            .then(resp => {
                if (resp.ok) return resp.json();
                throw new Error("데이터 응답 실패");
            })
            .then(dataList => {
                // 🔴 서버에서 받아온 JSON 데이터를 이용해 화면을 직접 그린다.
                dataList.forEach(fileName => {
                    const opt = document.createElement("option");
                    opt.value = fileName;
                    opt.textContent = fileName;
                    selectBox.appendChild(opt);
                });
            })
            .catch(error => console.error("오류:", error));
    }
</script>
</body>
</html>