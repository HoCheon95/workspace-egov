package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.buyer.service.BuyerService;

@WebServlet("/buyer/list")
public class BuyerListServlet extends HttpServlet{
    private BuyerService service = new BuyerServiceImpl();
    private ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 호출하여 회원 데이터 조회
        List<BuyerDto> buyerList = service.readBuyerList();

        // JSP 전달 준비
        req.setAttribute("buyerList", buyerList);

        // buyerList.jsp 화면 전환
        String lvn = "buyer/buyerList";
        viewResolver.resolveViewName(lvn, req, resp);
    }
}
