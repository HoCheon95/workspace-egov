package kr.or.ddit.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.IndexServlet;
import kr.or.ddit.auth.exception.SecuredResourceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AuthorizationFilter extends HttpFilter {
    private Map<String, List<String>> securedResoureces;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        String location = config.getInitParameter("location");
        securedResoureces = generateSecuredResources(location);
    }

    private Map<String, List<String>> generateSecuredResources(String location){
        String baseName = location;
        if(location == null) {
            throw new SecuredResourceNotFoundException();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(baseName);
        Map<String, List<String>> securedResources = new LinkedHashMap<>();
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String code = (String) keys.nextElement();
            String message = bundle.getString(code);
            List<String> roles = Arrays.asList(message.split(","));
            System.out.printf("%s : %s\n", code, roles);
            securedResources.put(code, roles);
        }
        return securedResources;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        boolean isSecuredResource = securedResoureces.containsKey(requestURI); // 보호자원 체크 (로그인 여부)
        boolean isAuthenticated = false; // 인증 (로그인 성공 여부)
        boolean isGranted = false; // 권한 (ADMIN, USER)

        if (isSecuredResource) {
            Principal authentication = req.getUserPrincipal();

            isAuthenticated = authentication != null;

            if (isAuthenticated) {
                List<String> grantedRoles = securedResoureces.get(requestURI);
                isGranted = grantedRoles.stream().anyMatch(role -> req.isUserInRole(role));
            }
        }
        boolean pass = !isSecuredResource || (isAuthenticated && isGranted);

        if (pass) {
            chain.doFilter(req, res);
        } else if (!isAuthenticated) {
            res.setStatus(401);
        } else {
            res.sendError(403, "권한 없음");
        }
    }
}
