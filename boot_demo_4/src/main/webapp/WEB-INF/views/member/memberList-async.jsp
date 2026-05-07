<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Member List Async</title>
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
                    <th>ID</th>
                    <th>회원명</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                    <th>거주지역</th>
                    <th>역할</th>
                </tr>
            </thead>
            <tbody id="list-body">

            </tbody>

            <tfoot>
                <tr>
                    <td colspan="7" style="text-align: center; gap: 10px; display: flex; justify-content: center; align-items: center;">
                        <div id="paging-area" data-pg-target="#searchForm1">



                        </div>
                    </td>
                    <div data-pg-form="#searchForm1">
                        <input type="text" name="memAdd1" placeholder="주소"/>
                        <input type="text" name="memName" placeholder="이름"/>
                        <button type="button" class="searchBtn">검색</button>
                    </div>
                </tr>
            </tfoot>
        </table>
        <%@ include file="/WEB-INF/fragments/postScript.jsp" %>

        <form id="searchForm1" method="get">
            <input type="text" name="memAdd1" readonly="true" />
            <input type="text" name="memName" readonly="true"/>
            <input type="text" readonly name="page" id="">
        </form>

        <script type="text/javascript" src="/js/app/commons/paging.js"></script>
        <script type="text/javascript" src="/js/app/member/memberList.js"></script>
    </body>
</html>