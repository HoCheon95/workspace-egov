package com.homework.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WorldTimeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 응답 시 한글 깨짐 방지를 위해 UTF-8 설정함
		resp.setContentType("text/html; charset=UTF-8");
		
		// 클라이언트가 보낸 파라미터(locale, timezone)를 읽어온다.
		String localeParam = req.getParameter("locale"); 
		String timezoneParam = req.getParameter("timezone"); 
		
		// 파라미터 값이 없을 경우를 대비한 기본값 처리나 예외 처리
		if (localeParam == null || timezoneParam == null) {
			resp.sendRedirect("index.html");
			return;
		}

		// 언어와 국가 코드를 분리하여 Locale 객체를 생성한다.
		String[] localeParts = localeParam.split("_");
		Locale locale = new Locale(localeParts[0], localeParts[1]);
		
		// 선택한 타임존의 현재 시간을 구한다.
		ZoneId zoneId = ZoneId.of(timezoneParam);
		ZonedDateTime now = ZonedDateTime.now(zoneId);
		
		// 해당 로케일의 언어 형식에 맞춰 날짜 포맷을 지정한다.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MMMM d일 (EEEE) a h시 mm분 ss초", locale);
		String formattedNow = now.format(formatter);
		
		// HTML 결과 화면을 동적으로 생성하여 응답한다.
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>세계 시간 결과</title></head><body>");
		out.print("<h2>🌍 세계 시간 조회 결과</h2>");
		out.print("<p><b>선택된 로케일:</b> " + locale.getDisplayName(Locale.KOREAN) + " (" + localeParam + ")</p>");
		out.print("<p><b>선택된 타임존:</b> " + timezoneParam + "</p>");
		out.print("<hr>");
		out.print("<h3>현재 시간: " + formattedNow + "</h3>");
		out.print("<br>");
		out.print("<a href='index.html'>🔙 다시 선택 화면으로 돌아가기</a>");
		out.print("</body></html>");
	}
}