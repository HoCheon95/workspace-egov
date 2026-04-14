package kr.or.ddit.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.member.dto.MemberDto;
import org.apache.commons.lang3.StringUtils;

public class UsernamePasswordAuthenticationFilter extends HttpFilter {
    private String loginPage;
    private String loginProcessUrl;
    private String loginFailureUrl;
    private String loginSuccessUrl;

    private AuthenticateService authservice = new AuthenticateService();

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        loginPage = config.getInitParameter("login-page");
        loginProcessUrl = config.getInitParameter("login-process-url");
        loginFailureUrl = config.getInitParameter("login-failure-url");
        loginSuccessUrl = config.getInitParameter("login-success-url");
    }

    private boolean isLoginRequest(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.contains(loginProcessUrl) && req.getMethod().equalsIgnoreCase("post"); // 로그인 처리 요청
    }
    
    @Override
    protected void doFilter (HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (isLoginRequest(req)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            HttpSession session = req.getSession(false);
            String lvn = null;

            if (session == null || session.isNew()) {
            	resp.sendError(400);
                return ;
            }
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            	session.setAttribute("", "아이디나 비밀번호 누락");
                lvn = loginFailureUrl;
            }
            else {
                try {
                    MemberDto authMember = authservice.authenticate(username, password);    
                    if (authMember != null) {
                        session.setAttribute("authMember", authMember);
                        lvn = loginSuccessUrl;
                    }
                } catch (AuthenticationException e) {
                    session.setAttribute("SECURITY_LAST_EXCEPTION", e);
                    lvn = loginFailureUrl;
                }
                
            }
            resp.sendRedirect(req.getContextPath() + lvn);
        }
        else {
            chain.doFilter(req, resp);
            if(resp.getStatus()==401 && !resp.isCommitted()){
                resp.sendRedirect(req.getContextPath() + loginPage);
            }
        }
    }
}
