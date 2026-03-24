package kr.or.ddit.servlet03;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Http(Hyper Text Transfer Protocol) : 하이퍼 텍스트로 구성된 메시지를 전송할 때 사용되는 패키징 규약
 * 
 * Http Request Packaging
 * 1. Request Line   : URL, Http Method, protocol/version
 *      Method : 요청의 목적(의도)과 패키징 구조를 결정함.
 *      GET (조회)
 *      POST (생성)
 *      -------------
 *      PUT / PATCH (수정)
 *      DELETE (삭제)
 * 
 *      OPTIONS : preFlight 요청에 사용됨. 대표적으로 CORS 에서 활용됨.
 *      HEAD    : 차후 응답이 구성될 떄 response body 가 없어도 되는 요청.
 *      TRACE(폐쇄 네트워크에서 제한된 요청으로 사용됨)   : 서버를 대상으로 트래킹을 하거나 디버깅을 할때의 요청.
 * 
 *      ex) 회원 조회/수정/등록/삭제
 *      /member/memberList.do : 조회
 *      /member/memberRegist.do : 등록
 *      /member/memberUpdate.do : 수정
 *      /member/memberDelete.do : 삭제
 *      
 *      RESTful URI 구조 설계
 *      /member GET
 *      /member POST
 *      /member PUT
 *      /member DELETE
 * 2. Request Header : 클라이언트와 요청에 대한 메타 데이터 [name / value]
 *      Accept-* : response 에 대한 부가정보를 설정해서 요청을 전송할때 활용
 *          ex) Accept : application/json (이후의 응답을 json 으로 수신하겠음을 표현함)
 *                     : 서버에서 처리할 수 없는 type에 대한 요청인 경우, 처리방식에 대한 고려가 필요함. ex) 406 에러...
 *          ex) Accept-Language : ko-KR (이후의 응답 로케일을 ko-KR 로 수신하겠음을 표현함)
 *      Content-* : requiest body 를 통해 전송되는 메시지(컨텐츠)에 대한 메타데이터
 *          ex) Content-Type
 *                  application/x-www-form-urlencoded : request body 에 요청 파라미터 형태의 데이터가 전송되고 있음.
 *                  multipart/form-data : 파일업로드와 같이 여러종류의 데이터를 하나의 요청으로 전송할때 사용됨.
 *                  application/json : request body 가 json payload 로 구성됨.
 *          ex) Content-Length
 *      User-Agent
 * 3. Request Body (Message Body, Content Body) : 실제 메시지의 본문(내용)
 */
@WebServlet("/request-desc")
public class RequestDescServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("조회");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("등록");
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("수정");
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("삭제");
    }
}
