package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BlindFilter extends HttpFilter{

    // 192.168.145.44
    private List<String> whitelist;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init();
        whitelist = Arrays.asList(config.getInitParameter("whitelist").split(","));
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String ip = req.getRemoteAddr();
        boolean pass = whitelist.contains(ip);
        
        if(pass){
            chain.doFilter(req,res);
        } else {
            res.sendError(400, "통과할 수 없음");
        }

    }
}
