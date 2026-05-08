<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
    <style>
        body { background-color: #f8f9fa; font-family: 'Pretendard', sans-serif; padding: 20px; }
        h1 { color: #343a40; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <h1>Welcome to the Index Page</h1>

    <p>${attrName}</p>
    <%-- <img src="/images/tmvlzl.png" alt=""> --%>
    <img src="/images/26147.png" alt="">
    <div style="display: flex; flex-direction: column; gap: 10px">
        <div>
            <a href="/buyer/list">Buyer List</a> | 
            <a href="/buyer/list/aggrid">Buyer List Aggrid</a> | 
            <a href="/buyer/insert">Buyer Insert</a>
        </div>

        <div>
            <a href="/prod/list">Product List</a> | 
            <a href="/prod/create">Product Create</a>
        </div>
        <div>
            <a href="/rest/lprods">Lprod List</a> | 
        </div>
        <div>
            <a href="/admin/memberList">Member List</a> | 
            <a href="/admin/memberList/type-async">Member List Async</a>
        </div>
        <div>
            <a href="/file/manager">File Manager</a>
        </div>
        <div>
            <a href="/member/regist">Sing Up</a>
        </div>
    </div>
<script src="/js/app/index.js"></script>
</body>
</html>