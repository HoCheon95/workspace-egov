package kr.or.ddit.hw02;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * locale과 timezone 이라는 파라미터로 결정된,
 * Locale 과 ZoneId 에 따른 시계 시간 서비스. 
 * timezone = Europe/London
 * locale = ko_KR, en_US, en_UK (locale code, language tag)
 */
@WebServlet("/hw02/worldtime")
public class WorldTimeModel2Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// JSR-310 방식의 기간 API 활용
		// 요청 파라미터로 변경
		// String timezone = req.getParameter("timezone");
		// ZoneId zone = ZoneId.of(timezone);
		ZoneId zone = Optional.ofNullable(req.getParameter("timezone"))
				.map(ZoneId::of)
				.orElse(ZoneId.systemDefault());

		LocalDateTime now = LocalDateTime.now(zone);
		
		// 요청 파라미터로 변경
		// String localeParam = req.getParameter("locale");
		// Locale locale = Locale.forLanguageTag(localeParam);
		
		Locale locale = Optional.ofNullable(req.getParameter("locale"))
								.map(Locale::forLanguageTag)
								.filter(l -> !l.getLanguage().isBlank())
								.orElse(req.getLocale());
		String formatted = 
				now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
						.localizedBy(locale)
				);

		String accept = req.getHeader("accept");

		if(accept.contains("json")){
			Map<String,Object> target = Map.of("now", formatted);
			String json = new Gson().toJson(target);
			resp.setContentType("application/json;charset=UTF-8");
			resp.getWriter().print(json);
		} else if(accept.contains("html")){
			req.setAttribute("now", formatted);
			String view ="/WEB-INF/views/hw02/world-time.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
	}
}
