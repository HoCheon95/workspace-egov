package kr.or.ddit.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutFilter extends HttpFilter {
    private String logoutSuccessUrl;
    private String logoutUrl;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        logoutSuccessUrl =config.getInitParameter("logout-success-url");
        logoutUrl =config.getInitParameter("logout-url");

    }

    @Override
    protected void doFilter (HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        
        if(requestURI.contains(logoutUrl)){
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + logoutSuccessUrl);
        } else {
            chain.doFilter(req, resp);
        }


    }
}
