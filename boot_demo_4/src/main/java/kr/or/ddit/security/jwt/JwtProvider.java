package kr.or.ddit.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.security.users.MemberDtoWrapper;

@Component
public class JwtProvider {
    public static final int EXPIRATIONTIME = 60 * 1000 * 30;
    @Value("${jwt.secret-key}")
    private byte[] sharedSecret;

    public String generateToken(Authentication authentication) {
        MemberDtoWrapper principal =(MemberDtoWrapper) authentication.getPrincipal();
        MemberDTO realUser = principal.getRealUser();

        try {
            // 자기 서명된 JWT 를 생성하기 위한 서명자
            JWSSigner signer = new MACSigner(sharedSecret);
            
            // payload 정의
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(authentication.getName())
                .claim("name", realUser.getMemName())
                .claim("roles", authentication.getAuthorities().stream()
                                    .map(ga -> ga.getAuthority())
                                    .toList()
                )
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + EXPIRATIONTIME))
                .build();
            
            // JWT : header + payload
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            
            // JWT 서명 -> 서명된 jwt
            signedJWT.sign(signer);
            
            // token 으로 직렬화
            String token = signedJWT.serialize();
            
            return token;
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
