<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>자원의 접근 경로에 따른 분류</h4>
    <pre>
        FileSystem Resource : 물리적인 절대 경로(file system path)를 통해 접근할 수 있는 자원
            ex) \\wsl.localhost\Ubuntu\home\ho\workspace\00.medias\images\cat1.jpg
        Classpath Resource : classpath 이후의 qualified name을 통해 접근할 수 있는 자원
            ex) /kr/or/ddit/dummy.properties
        Web Resource : URL 을 통해 접근할 수 있는 네트워크에 공개된 자원
            ex) http://localhost:8080/03/echo
        
        URI (Uniform Resource Identifier) : URL 을 포함한 자원의 식별 방법에 대한 포괄적인 명칭
        URL(~ Locatoer)
            protocaol//host[:prot][pathname]
                pathname = context path + resource path. /03/resourceIdentity.jsp
                    1. Origin (출입증/신분증): https://localhost:8080
                    2. Pathname (상세 경로): /03/resource.jsp
                        Context Path (/WebStudy01): 프로젝트 이름표예요. 서버에 여러 프로젝트가 있을 때 내 프로젝트를 찾아가는 첫 관문이죠.
                        Resource Path (/03/resource.jsp): 프로젝트 내부의 진짜 방 번호예요.

                origin = protocol + host + port
                URL (절대경로1) = origin + pathname <a href="https://www.naver.com/">네이버</a>
                URL (절대경로2)= pathname (동일 출처의 자원에 접근하는 경우.) 
                        <a class="dummy" href="http://localhost:8080/03/data-form.jsp">data-form.jsp</a>
                        <a class="dummy" href="/03/data-form.jsp">data-form.jsp</a>
                    상대경로 : 현재 자원의 위치에 따라 절대 경로가 완성됨
                        <a class="dummy" href="data-form.jsp">data-form.jsp</a>
                SOP(Same Origin Policy) 란 ? UI 자원을 제공한 출처(origin) 과 데이터를 제공한 출처(origin) 이 동일해야 함
    </pre>
    <script>
        document.querySelectorAll(".dummy").forEach((a)=>console.log(a.href));
    </script>
</body>
</html>


