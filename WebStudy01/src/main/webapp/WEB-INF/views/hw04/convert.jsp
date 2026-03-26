<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>단위 변환기</title>
    </head>
    <body>
        <form method="post" action="convert">
            <input type="text" name="value" placeholder="변환 수치" />

            <select name="from">
                <c:forEach items="${unitGroup}" var="entry">
                    <c:set value="${entry.key}" var="unitType"/>
                    <c:set value="${entry.value}" var="unitList"/>
                    
                    <optgroup label="${unitType}">
                        <c:forEach items="${unitList}" var="unit">
                            <option value="${unit}">${unit}</option>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>

            <span> to </span>

            <select name="to">
                <c:forEach items="${unitGroup}" var="entry">
                    <c:set value="${entry.key}" var="unitType"/>
                    <c:set value="${entry.value}" var="unitList"/>
                    
                    <optgroup label="${unitType}">
                        <c:forEach items="${unitList}" var="unit">
                            <option value="${unit}">${unit}</option>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>

            <button type="submit">전송</button>
        </form>
    </body>
</html>