<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    response.setHeader("Cache-Control", "no-store");
    response.addHeader("Cache-Control", "no-cache");
    response.setIntHeader("Refresh", 1);
%>
<%=LocalDateTime.now()%>