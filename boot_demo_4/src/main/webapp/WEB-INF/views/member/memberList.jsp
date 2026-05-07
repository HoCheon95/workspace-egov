<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Member List</title>

    <style>
    table {
  width: 80%;
  border-collapse: collapse; /* Removes double borders */
  table-layout: fixed;      /* Faster rendering & fixed widths */
  margin:auto;
}

th, td {
  padding: 12px;
  text-align: center;
  border-bottom: 1px solid #ddd; /* Horizontal dividers only */
}

thead th {
  background-color: #f2f2f2;
  font-weight: bold;
}

/* Zebra Striping */
tbody tr:nth-child(even) {
  background-color: #fafafa;
}

/* Interaction */

    </style>
<%@ include file="/WEB-INF/fragments/preCss.jsp" %>
</head>
<body>
    <h1>Member List</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>번호</th>
                <th style="width: 80px;">ID</th>
                <th style="width: 120px;">회원명</th>
                <th style="width: 200px;">전화번호</th>
                <th style="width: 200px;">이메일</th>
                <th>거주지역</th>
                <th>역할</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty memberList}">
                    <c:forEach var="member" items="${memberList}">
                        <tr>
                            <td>${member.rnum}</td>
                            <td>${member.memId}</td>
                            <td>${member.memName}</td>
                            <td>${member.memHp}</td>
                            <td>${member.memMail}</td>
                            <td style="text-align: left;">${member.memAdd1} ${member.memAdd2} ${member.memZip}</td>
                            <td>${member.memRoles}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">조건에 맞는 회원 없음</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            
        </tbody>
        <tfoot>
                <tr>
                    <td colspan="8" style="text-align: center; gap: 10px; display: flex; justify-content: center; align-items: center;">
                    ${pagingHtml}
                    </td>
                    <div id="searchUi">
                        <%-- <select name="searchType">
                            <option value="">전체</option>
                            <option value="name" ${search.searchType eq 'name' ? 'selected' : ''}>이름</option>
                            <option value="address" ${search.searchType eq 'address' ? 'selected' : ''}>주소</option>
                        </select>
                        <input type="text" name="searchWord" id="" placeholder="검색어를 입력하세요" value="${search.searchWord}"/> --%>
                        <form:input type="text" path="detailSearch.memAdd1" placeholder="주소" />
                        <form:input type="text" path="detailSearch.memName" placeholder="이름"/>
                        <button type="button" id="searchBtn">검색</button>
                    </div>
                </tr>
            </tfoot>
    </table>
    <%@ include file="/WEB-INF/fragments/postScript.jsp" %>

    <form:form modelAttribute="detailSearch" id="searchForm" method="get" >
        <%-- <input type="text" readonly name="searchType" id="" value="${search.searchType}"> 
        <input type="text" readonly name="searchWord" id="" value="${search.searchWord}"> --%>
        <form:input path="memAdd1" readonly="true" />
        <input type="text" name="memName" value="${detailSearch.memName}" readonly/>
        <input type="text" readonly name="page" id="">
    </form:form>

    <script>
     const searchUi = document.getElementById("searchUi");
     const searchForm = document.getElementById("searchForm");
        searchUi.addEventListener("click", (event) => {
            if (event.target.id !== "searchBtn") return false;
            searchUi.querySelectorAll("[name]").forEach((input)=> {
                searchForm[input.name].value = input.value;
            });
            searchForm.requestSubmit();
        });

    const fnPaging = (page) => {
        searchForm.page.value = page;
        searchForm.requestSubmit();
    }

    </script>
</body>
</html>