package kr.or.ddit.security.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.security.jwt.JwtProvider;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        return "/";
    }

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {

        // 1. Authentication -> JWT 생성
        String token = jwtProvider.generateToken(authentication);
        // 2. access-token 쿠키로 전송
        Cookie accessToken = new Cookie("access-token", token);
        accessToken.setPath("/");
        accessToken.setMaxAge(JwtProvider.EXPIRATIONTIME);
        accessToken.setHttpOnly(true);
        // dummyCookie.setSecure(true);
        response.addCookie(accessToken);

        Cookie dummyCookie = new Cookie("dummy", "cookievalue");
        dummyCookie.setPath("/");
        dummyCookie.setMaxAge(60*30);
        dummyCookie.setHttpOnly(true);
        // dummyCookie.setSecure(true);
        response.addCookie(dummyCookie);
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
