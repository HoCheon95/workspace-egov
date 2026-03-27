package kr.or.ddit.hw05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.or.ddit.hw05.domain.ConvertablePair;
import kr.or.ddit.hw05.dto.ExchangeRequest;
import kr.or.ddit.hw05.dto.ExchangeResponse;
import kr.or.ddit.hw05.service.ExchangeService;
import kr.or.ddit.hw05.validate.ExchangeValidator;

// 브라우저에서 /hw05/exchange 주소로 접속하면 이 서블릿이 동작함
@WebServlet("/hw05/exchange")
public class CurrencyExchangeServlet extends HttpServlet{

    /**
     * GET 요청 처리 - 사용자가 주소창에 URL을 입력하거나 페이지를 처음 열 때 실행됨
     * 역할: 환전 가능한 통화 목록을 만들어서 JSP(화면)로 전달
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 중복 없이 통화를 담을 Set 생성 (LinkedHashSet은 넣은 순서를 유지함)
        Set<Currency> currencySet = new LinkedHashSet<>();

        // ExchangeService에 등록된 환전 쌍(KRW→USD, USD→KRW 등)을 순서대로 꺼내서
        // from(출발 통화)과 to(도착 통화)를 모두 Set에 추가 → 중복 자동 제거됨
        for (ConvertablePair pair : ExchangeService.converterMap.keySet()) {
            currencySet.add(pair.getFrom());
            currencySet.add(pair.getTo());
        }

        // Set → List로 변환 (JSP에서 반복문 돌리기 편하게)
        List<Currency> currencyList = new ArrayList<>(currencySet);

        // JSP에서 ${currencyList} 로 꺼내 쓸 수 있도록 request에 저장
        req.setAttribute("currencyList", currencyList);

        // exchange.jsp 화면으로 이동 (forward = 주소창 URL은 그대로 유지)
        req.getRequestDispatcher("/WEB-INF/views/hw05/exchange.jsp").forward(req, resp);
    }

    /**
     * POST 요청 처리 - 사용자가 환전 폼을 작성하고 버튼을 눌렀을 때 실행됨
     * 역할: 입력값을 받아 유효성 검사 → 환전 계산 → 결과를 JSP로 전달
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글 깨짐 방지 (폼 데이터 인코딩을 UTF-8로 설정)
        req.setCharacterEncoding("UTF-8");

        // 폼에서 전송된 값 꺼내기 (HTML input의 name 속성값과 일치해야 함)
        String amountStr = req.getParameter("amount"); // 금액 (예: "150000")
        String fromCode  = req.getParameter("from");   // 출발 통화 코드 (예: "KRW")
        String toCode    = req.getParameter("to");     // 도착 통화 코드 (예: "USD")

        try {
            // 1단계: 입력값 검증 - 숫자인지, 올바른 통화코드인지 확인
            //        문제가 있으면 RuntimeException 을 던져서 catch 블록으로 이동
            ExchangeRequest requestDto = new ExchangeValidator().validate(amountStr, fromCode, toCode);

            // 2단계: 실제 환전 계산 (req.getLocale() = 사용자 브라우저의 지역/언어 설정)
            ExchangeResponse responseDto = new ExchangeService().exchange(requestDto, req.getLocale());

            // 계산 결과를 JSP에서 ${exchangeResponse} 로 꺼내 쓸 수 있도록 저장
            req.setAttribute("exchangeResponse", responseDto);

        } catch (RuntimeException e) {
            // 검증 실패 또는 계산 오류 시 에러 메시지를 JSP에서 ${error} 로 표시
            req.setAttribute("error", e.getMessage());
        }

        // 성공이든 실패든 doGet()을 호출해서 화면(exchange.jsp)을 다시 보여줌
        doGet(req, resp);
    }
}
