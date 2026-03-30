package kr.or.ddit.hw05;

import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.hw05.dto.ExchangeRequest;
import kr.or.ddit.hw05.dto.ExchangeResponse;
import kr.or.ddit.hw05.service.ExchangeService;
import kr.or.ddit.hw05.validate.ExchangeValidator;

// 브라우저에서 /hw05/exchange 주소로 접속하면 이 서블릿이 동작함
@WebServlet("/hw05/exchange")
public class CurrencyExchangeServlet extends HttpServlet{
    private ExchangeService service = new ExchangeService();
    private ExchangeValidator validator = new ExchangeValidator();
    private Gson gson = new Gson();
    private final String MODELNAME = "resultDto";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ExchangeResponse respDto = (ExchangeResponse) session.getAttribute(MODELNAME);
        req.setAttribute(MODELNAME, respDto);
        session.removeAttribute(MODELNAME); // flash attribute


        List<Currency> currencies = service.getConvertableCurrencies();
        req.setAttribute("currencies", currencies);
        String view = "/WEB-INF/views/hw05/exchange.jsp";
        req.getRequestDispatcher(view).forward(req, resp);
    }

    private ExchangeRequest getDtoFromParameters(HttpServletRequest req) {
        ExchangeRequest reqDto = validator.validate(
            req.getParameter("amount"),
            req.getParameter("from"),
            req.getParameter("to")
        );
        return reqDto;
    }

    private ExchangeRequest getDtoFromJson(HttpServletRequest req) {
        try{
            return gson.fromJson(req.getReader(), ExchangeRequest.class);
        } catch (IOException | JsonSyntaxException | JsonIOException e){
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * POST 요청 처리 - 사용자가 환전 폼을 작성하고 버튼을 눌렀을 때 실행됨
     * 역할: 입력값을 받아 유효성 검사 → 환전 계산 → 결과를 JSP로 전달
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. request content-tupe, accept 확인
        String contentType = Optional.ofNullable(req.getContentType()).orElse("application/x-www-form-urlencoded");
        String accept = Optional.ofNullable(req.getHeader("accept")).orElse("text/html");
        // 2. 파라미터 혹은 json 수신
        ExchangeRequest reqDto = null;
        int status = 200;
        String message = null;
        // 3. 검증 -> ExchangeRequest
        try{
            if(contentType.contains("json")) {
                reqDto = getDtoFromJson(req);
            } else if(contentType.contains("x-www-form-urlencoded")) {
                reqDto = getDtoFromParameters(req);
            } else {
                status = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
                message="%s서버에서 읽을 수 없음 body 컨텐츠임.".formatted(contentType);
            }

            if(status == 200) {
                // 4. 변환(service logic) -> ExchangeResponse
                ExchangeResponse respDto = service.exchange(reqDto, req.getLocale());
                // 5. 협상 결과에 따라 html/json 응답   
                if(accept.contains("json")) {
                    handleJson(respDto, resp);
                    return;
                } else if (accept.contains("html")) {
                    handleHtml(respDto, req, resp);
                    return;
                } else {
                    status = HttpServletResponse.SC_NOT_ACCEPTABLE;
                    message = "%s 컨텐츠 형식은 서버에서 생성할 수 없음.".formatted(accept);
                }
            }
        } catch (IllegalArgumentException e) {
            // 3-1. 검증 실패(400 상태코드와 에러 메시지)
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getMessage();
        }

        if(accept.contains("json")) {
            resp.setStatus(status);
            Object errorResp = Map.of("status", status, "message", message);
            handleJson(errorResp, resp);
        } else {
            resp.sendError(status, message);
        }
    }

    private void handleJson(Object nativeTarget, HttpServletResponse resp) throws JsonIOException, IOException{
        resp.setContentType("application/json");
        gson.toJson(nativeTarget, resp.getWriter());
    }
    private void handleHtml(ExchangeResponse respDto, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().setAttribute(MODELNAME, respDto);
        String location = req.getContextPath() + "/hw05/exchange";
        resp.sendRedirect(location);

        // req.setAttribute(MODELNAME, respDto);
        // String view = "/WEB-INF/views/hw05/exchange.jsp";
        // req.getRequestDispatcher(view).forward(req, resp);
    }
}
