package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.servlet03.dto.DummyRequest;

@WebServlet("/03/request-data")
public class RequestDataReceiveServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // request line : URL, Method, Protocal
        String uri = req.getRequestURI();
        String method = req.getMethod();
        String protocol = req.getProtocol();
        System.out.printf("protocol : %s, uri : %s, method : %s \n", protocol, uri, method);
        String queryString = req.getQueryString();
        System.out.printf("query string : %s \n", queryString);

        // request header : accept-*, content-*, user-agent...
        String accept = req.getHeader("accept");
        Locale acceptLanguage = req.getLocale();
        String contentType = req.getContentType();
        long contentLength = req.getContentLengthLong();
        String userAgent = req.getHeader("user-agent");
        System.out.printf("content-length : %d, content-type : %s \n", contentLength, contentType);

        // request body
        // url encoding 된 문자열 파라미터 집합 : name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=34
        // json payload 형태 : {"name" : "홍길동", "age" : "34"}
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((key, values) -> {
        System.out.printf("key : %s, values : %s\n", key, Arrays.toString(values));
        });
        System.out.println("=====================body=====================");
        // req.getReader().lines().forEach(System.out::println);

        // if ("post".equalsIgnoreCase(method) && contentType.contains("json")) {
        //     receiveJsonPayload(req);
        // } else if ("post".equalsIgnoreCase(method) && contentType.contains("urlencoded")) {
        //     receiveParameters(req);
        // } else if ("get".equalsIgnoreCase(method)) {
        //     receiveParameters(req);
        // }
    }

    /**
     * 파라미터 맵을 수신하고, 콘솔에 출력
     * 1. GET + query string
     * 2. POST + url encoded content type
     */
    private void receiveParameters(HttpServletRequest req) {
        String name = req.getParameter("name");
        String ageParam = req.getParameter("age");
        int age = Integer.parseInt(ageParam);
        DummyRequest reqDto = new DummyRequest(name, age);
        System.out.printf("수신한 parameters : %s\n", reqDto);

    }

    /***
     * json payload 를 수신하고, 콘솔에 출력
     * 3. POST + json content type
     * @param req
     * @throws IOException
     */
    private void receiveJsonPayload(HttpServletRequest req) throws IOException {
        // json 수신 -> 역직렬화 -> java object (XXXRequest dto)
        DummyRequest reqDto =  new Gson().fromJson(req.getReader(), DummyRequest.class);
        System.out.printf("수신한 json 객체 : %s\n", reqDto);
    }
}
