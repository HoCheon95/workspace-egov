package kr.or.ddit.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharacterEncodingFilter extends HttpFilter {
    private String encoding;

    @Override
    public void init (FilterConfig config) throws ServletException {
        super.init(config);
        encoding = config.getInitParameter("encoding");
    }

    @Override
    protected void doFilter (HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        req.setCharacterEncoding(encoding);
        res.setCharacterEncoding(encoding);

        chain.doFilter(req, res);
    }
}
