<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>제조사 상세 정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">제조사 상세 정보</h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered border-primary-subtle">
                    <colgroup>
                        <col style="width: 20%; background-color: #f8f9fa;">
                        <col style="width: 30%;">
                        <col style="width: 20%; background-color: #f8f9fa;">
                        <col style="width: 30%;">
                    </colgroup>
                    <tbody>
                        <tr>
                            <th class="text-center">제조사명</th>
                            <td>${buyer.buyerName}</td>
                            <th class="text-center">제조사 ID</th>
                            <td>${buyer.buyerId}</td>
                        </tr>
                        <tr>
                            <th class="text-center">분류코드</th>
                            <td><span class="badge bg-secondary">${buyer.lprod.lprodGu}</span></td>
                            <th class="text-center">분류명</th>
                            <td>${buyer.lprod.lprodName}</td>
                        </tr>
                        <tr>
                            <th class="text-center">담당자명</th>
                            <td>${buyer.buyerCharger}</td>
                            <th class="text-center">이메일</th>
                            <td><a href="mailto:${buyer.buyerMail}">${buyer.buyerMail}</a></td>
                        </tr>
                        <tr>
                            <th class="text-center">소재지</th>
                            <td colspan="3">${buyer.buyerAdd1} ${buyer.buyerAdd2}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="card-footer text-end">
                <a href="${pageContext.request.contextPath}/buyer/list" class="btn btn-secondary">목록으로</a>
                <button class="btn btn-warning">수정하기</button>
            </div>
        </div>
    </div>
</body>
</html>