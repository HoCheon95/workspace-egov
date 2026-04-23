<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>회원 목록 조회</title>

        <script text="text/javascript">
            alert("회원 목록!");
        </script>

        <style>
            table { border-collapse: collapse; width: 100%; }
            th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
            th { background-color: #f4f4f4; }
        </style>
    </head>
    <body>
        <%--  테이블 태그 시작 (전체를 감싸야 한다) --%>
        <table class="table table-striped"> 
            <thead class="thead-dark">
                <tr>  <%--  th는 반드시 tr로 감싸야 한다 --%>
                    <th>회원명</th>
                    <th>휴대폰</th>
                    <th>이메일</th>
                    <th>거주지역</th>
                    <th>역할</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <%-- 리스트가 비어있지 않을 때만 반복문을 실행한다 --%>
                    <c:when test="${not empty memberList}">
                        <c:forEach items="${memberList}" var="member">
                            <tr>
                                <td>${member.memName}</td>
                                <td>${member.memHp}</td>
                                <td>${member.memMail}</td>
                                <td>${member.memAdd1}</td>
                                <td>${member.memRoles}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <%--  데이터가 없을 때의 처리 --%>
                    <c:otherwise>
                        <tr>
                            <td colspan="5">조건에 맞는 회원 없음.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </body>
</html>