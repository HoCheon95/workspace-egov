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

@WebServlet("/hw05/exchange")
public class CurrencyExchangeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 지원 가능한 환전 통화를 수집
        Set<Currency> currencySet = new LinkedHashSet<>();
        for (ConvertablePair pair : ExchangeService.converterMap.keySet()) {
            currencySet.add(pair.getFrom());
            currencySet.add(pair.getTo());
        }

        List<Currency> currencyList = new ArrayList<>(currencySet);
        req.setAttribute("currencyList", currencyList);

        req.getRequestDispatcher("/WEB-INF/views/hw05/exchange.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String amountStr = req.getParameter("amount");
        String fromCode = req.getParameter("from");
        String toCode = req.getParameter("to");

        try {
            ExchangeRequest requestDto = new ExchangeValidator().validate(amountStr, fromCode, toCode);
            ExchangeResponse responseDto = new ExchangeService().exchange(requestDto, req.getLocale());

            req.setAttribute("exchangeResponse", responseDto);
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
        }

        doGet(req, resp);
    }
}
