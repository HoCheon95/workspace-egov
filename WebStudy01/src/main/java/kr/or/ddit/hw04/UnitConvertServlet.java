package kr.or.ddit.hw04;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.hw04.domain.Unit;
import kr.or.ddit.hw04.domain.UnitType;
import kr.or.ddit.hw04.dto.ConversionRequest;
import kr.or.ddit.hw04.dto.ConversionResponse;
import kr.or.ddit.hw04.dto.ErrorResponse;
import kr.or.ddit.hw04.exception.UnitConversionException;
import kr.or.ddit.hw04.service.UntiConversionService;
import kr.or.ddit.hw04.validation.ConversionValidator;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/hw04/convert")
public class UnitConvertServlet extends HttpServlet {
    private ViewResolver viewResolver = new ViewResolverComposite();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        ConversionResponse respDto = (ConversionResponse) session.getAttribute("convertResult");
        session.removeAttribute("convertResult"); // flash attribute
        req.setAttribute("convertResult", respDto);

        Map<UnitType, List<Unit>> unitGroup = Arrays.stream(Unit.values())
                .collect(Collectors.groupingBy(u -> {
                    System.out.println(u);
                    return u.getType();
                }));
        req.setAttribute("unitGroup", unitGroup);
        String view = "/WEB-INF/views/hw04/convert.jsp";
        req.getRequestDispatcher(view).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int status = 200;

        // 1. 3개의 파라미터 수신(value, from, to)
        String value = req.getParameter("value");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        ConversionRequest reqDto = null;
        Object nativeTarget = null;
        
        // 2. 요청 검증 후 request DTO 로 반환
        try {
             reqDto = ConversionValidator.validate(value, from, to);

            // 3. 실제 변환 로직 사용, request DTO 를 넘겨서 response DTO 반환
            UntiConversionService service = new UntiConversionService();
            ConversionResponse respDto = service.convert(reqDto, req.getLocale());

            // 4. response DTO 직렬화해 json 으로 응답
            nativeTarget = respDto;            

        } catch (UnitConversionException e) {
            // * 검증에 통과하지 못하면? 예외를 캐치하고 400 에러로 변환
            status = 400;
            nativeTarget = new ErrorResponse(400, e.getMessage(), reqDto);
        }

        String accept = req.getHeader("accept");

        if(accept.contains("json")) {
            resp.setStatus(status);
            handleJson(nativeTarget, resp);
            return;
        }

        if(status!=200){
            resp.sendError(status);
            return;
        }

        if(accept.contains("html")) {
            // req.setAttribute("convertResult", nativeTarget);
            req.getSession().setAttribute("convertResult", nativeTarget);
            handleHtml(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    private void handleHtml(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String view = "/WEB-INF/views/hw04/convert.jsp";
        // req.getRequestDispatcher(view).forward(req, resp);
        String logicalViewName = "redirect:/hw04/convert";
        viewResolver.resolveViewName(logicalViewName, req, resp);

        // String location = req.getContextPath() + "/hw04/convert";
        // resp.sendRedirect(location);
    }

    private void handleJson(Object nativeTarget, HttpServletResponse resp) throws JsonIOException, IOException{
        resp.setContentType("application/json");
        new Gson().toJson(nativeTarget, resp.getWriter());
    }
}
