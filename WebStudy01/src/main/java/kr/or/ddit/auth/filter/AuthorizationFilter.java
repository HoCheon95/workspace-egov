package kr.or.ddit.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AuthorizationFilter extends HttpFilter {
    private Map<String, List<String>> securedResoureces;

@Override
    public void init (FilterConfig config) throws ServletException {
        super.init(config);
        securedResoureces = new LinkedHashMap<>();
        securedResoureces.put("/hw04/convert", List.of("ROLE_USER", "ROLE_ADMIN"));
        securedResoureces.put("/hw05/exchange", List.of("ROLE_ADMIN"));
    }
    @Override
    protected void doFilter (HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        boolean isSecuredResource = securedResoureces.containsKey(requestURI); // 보호자원 체크 (로그인 여부)
        boolean isAuthenticated = false; // 인증 (로그인 성공 여부)
        boolean isGranted = false; // 권한 (ADMIN, USER)

        if(isSecuredResource){
            Principal authentication = req.getUserPrincipal();

            isAuthenticated = authentication != null;

            if(isAuthenticated){
                List<String> grantedRoles = securedResoureces.get(requestURI);
                isGranted =grantedRoles.stream().anyMatch(role->req.isUserInRole(role));
            }
        } 
        boolean pass = !isSecuredResource || (isAuthenticated && isGranted);

        if (pass) {
            chain.doFilter(req, res);
        } else if (!isAuthenticated){
            res.setStatus(401);
        } else {
            res.sendError(403, "권한 없음");
        }
    }
}
