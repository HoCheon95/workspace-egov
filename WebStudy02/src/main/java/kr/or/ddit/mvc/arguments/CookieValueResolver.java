package kr.or.ddit.mvc.arguments;

import java.io.IOException;
import java.net.URLDecoder;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieValueResolver {
    public String resolveCookieValue(String cookieName, HttpServletRequest req) throws IOException{
        Cookie[] cookies = req.getCookies();
        String cookieValue = null;
        if(cookies != null) {
            for(Cookie single : cookies) {
                if(cookieName.equals(single.getName())){
                    cookieValue = URLDecoder.decode(single.getValue(), "UTF-8");
                    break;
                }
            }
        }
        return cookieValue;
    }
}
