<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.net.URI" %>
<%@ page import="kr.or.ddit.servlet03.EchoServlet" %>
<%@ page import="java.nio.file.StandardCopyOption" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
    </head>
    <body>
        <pre>
            FileSystem Resource : 물리적인 절대 경로(file system path)를 통해 접근할 수 있는 자원
                ex) \\wsl.localhost\Ubuntu\home\ho\workspace\00.medias\images\cat1.jpg
            <%--
                String physicalPath = "/home/ho/workspace/00.medias/images/cat1.jpg";
                File cat1 = new File(physicalPath);--
                out.println(cat1.length());
                Path cat1Path = Paths.get(physicalPath);
                out.println(Files.size(cat1Path));
                Path destPath = Paths.get("/home/ho/", cat1Path.getFileName().toString());
                out.println(destPath);
                Files.copy(cat1Path, destPath);
            --%>
            Classpath Resource : classpath 이후의 qualified name을 통해 접근할 수 있는 자원
                ex) /kr/or/ddit/dummy.properties
            <%--
                String logicalPath = "/resources/kr/or/ddit/dummy.properties";
                URI classpathURI = EchoServlet.class.getResource(logicalPath).toURI();
                Path classPathResPath = Paths.get(classpathURI);
                out.println(classPathResPath);
                Path destPath = Paths.get("/home/ho/", classPathResPath.getFileName().toString());
                out.println(destPath);
                Files.copy(classPathResPath, destPath);
            --%>
            Web Resource : URL 을 통해 접근할 수 있는 네트워크에 공개된 자원
                ex) https://pokeapi.co/api/v2/pokemon/ditto
            <%
                String logical = "https://pokeapi.co/api/v2/pokemon/ditto";
                URL url = URI.create(logical).toURL();
                InputStream is = url.openStream();
                out.println(is.available());
                Path destPath = Paths.get("/home/ho/", "ditto.json");
                Files.copy(is, destPath);
            %>
        </pre>
    </body>
</html>