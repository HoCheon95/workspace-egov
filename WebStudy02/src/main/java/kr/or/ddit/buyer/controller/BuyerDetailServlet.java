package kr.or.ddit.buyer.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/buyer/detail")
public class BuyerDetailServlet extends HttpServlet{
    private BuyerService service = new BuyerServiceImpl();
    private ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 파라미터 조회
        String buyerId = req.getParameter("what");

        // 파라미터 유효성 검사
        if(StringUtils.isBlank(buyerId)) {
            resp.sendError(400, "필수 파라미터 누락");
            return;
        }
        
        // 호출하여 회원 데이터 조회
        BuyerDto buyer =service.readBuyer(buyerId);
        
        // JSP 전달 준비
        req.setAttribute("buyer", buyer);
        
        // buyerDetail.jsp 화면 전화
        String lvn = "buyer/buyerDetail";
        viewResolver.resolveViewName(lvn, req, resp);
    }
}
