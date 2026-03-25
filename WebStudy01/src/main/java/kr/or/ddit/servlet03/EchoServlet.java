package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.servlet03.dto.EchoRequest;
import kr.or.ddit.servlet03.dto.EchoResponse;
import kr.or.ddit.servlet03.service.EchoService;

/**
 * JSON 을 수신하고(역직렬화, request DTO), 가공후, 
 * JSON 을 송신(직렬화, response DTO)할 서블릿
 */
@WebServlet("/03/echo")
public class EchoServlet extends HttpServlet{
    private EchoService service = new EchoService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. content-type 헤더로 수신할 수 있는 요청인지 여부 파악
        // application/json (0), application/xml (X, 415)
        Gson gson = new Gson();
        String contentType = req.getContentType();
        String accept = req.getHeader("accept");
        if(!contentType.contains("application/json")) {
            // resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "읽을 수 없는 본문 형식임.");
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            Map<String, Object> errors = Map.of("status", HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "message", "읽을 수 없는 본문 형식임.");
            gson.toJson(errors, resp.getWriter());
            return;
        }

        if(!accept.contains("json")){
            // resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "처리할 수 없는 응답 형식임.");
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            Map<String, Object> errors = Map.of("status", HttpServletResponse.SC_NOT_ACCEPTABLE, "message", "처리할 수 없는 응답 형식임.");
            gson.toJson(errors, resp.getWriter());
            return;
        }

        // 2. 역직렬화 json -> native object
        EchoRequest reqDto = gson.fromJson(req.getReader(), EchoRequest.class);

        // 사용자 데이터에 대한 검증
        if (reqDto.getMessage() == null || reqDto.getLanguage() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, Object> errors = 
                Map.of("status", HttpServletResponse.SC_BAD_REQUEST, "message", "프로퍼티가 검증을 통과하지 못했음.");
            gson.toJson(errors, resp.getWriter());
            return;
        }

        // 3. 비즈니스 로직 객체 활용
        EchoResponse respDto = service.processEcho(reqDto);

        // 4. 직렬화 native object -> json
        resp.setContentType("application/json;charset=UTF-8");
        gson.toJson(respDto, resp.getWriter());
        
    }
}
