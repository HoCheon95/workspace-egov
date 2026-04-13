<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> <%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>JSP(Java Server Page)</h4>
    <pre>
        : 서블릿에서 확장된 파생 스펙으로 템플릿엔진으로 HTML 형태의 UI 컨텐츠를 생성할 목적으로 정의된 스크립트 형태의 언어.
        : 서블릿의 단점을 보완하고, 모델2구조로 책임을 분리하기 위해 사용됨.

        JSP 표준 구성 요소
        1. 정적 요소 : HTML, CSS, JavaScript, 텍스트...
        2. 동적 요소 : background / server side 에서 동작하는 코드.
            1) 스크립틀릿(scriptlet) :
            <% //java code : 지역코드화
                String local = "LOCAL";
                
            %>
            2) 표현식(expression)    : <%=local %>
            3) 지시자(directive)     :
            4) 선언부(declaration)   :
            <%!
            // 전역 코드화
                String global = "GLOBAL";
                void test(){}
            %>
            5) 주석(comment)
            <!-- HTML -->

            <script>
            // JS 주석
            </script>

            <style>
            /* CSS */
            </style>

            <%
                // Java single line 주석
                /*
                multi line 주석
                */
               request.setAttribute("local", "이건 속성이야!");
               Map<String,Object> sampleMap = Map.of("key1", "값1", "key-2", "값2");
               request.setAttribute("sMap", sampleMap);
            %>

            <%-- JSP 주석--%>
            6) EL(Expression Language)  : 표현식의 대체, <%=local %>, ${local}
                    model2 구조에서 SCOPE 에 저장된 속성을 조회하는 용도로 사용됨.
            7) JSTL(custom tag library) : 스크립틀릿 대체 (속성-attribute 사용, 조건문, 반복문), taglib 커스텀 태그 로딩이 필요함.
                커스텀 태그 : 필요에 의해 새로 정의된 태그 <%--<prefix:tagname>--%>
                <c:set var="attr" value="속성값" scope="request"/>
                <c:set var="tokens" value="100,200,300" scope="request"/>
                ${attr}
                <c:forTokens items="${tokens}" delims="," var="tkn">
                    ${tkn * 10}
                </c:forTokens>
                ${sMap.key-2}, ${sMap["key-2"]}
                

    </pre>
    <script>
        let dummy = "<%=local%>";
    </script>
</body>
</html>