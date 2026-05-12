package kr.or.ddit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration // 이 클래스가 스프링의 환경설정 파일임을 알려줍니다.
@EnableWebSecurity // 스프링 시큐리티 필터 체인을 활성화합니다. (debug=true로 하면 콘솔에 시큐리티 로그가 상세히 나옵니다.)
@EnableMethodSecurity // 컨트롤러의 메서드 단에서 @PreAuthorize, @PostAuthorize 어노테이션으로 권한을 제어할 수 있게 해줍니다.
public class SecurityConfig {

   // application.properties에 설정한 DB 연결 정보(spring.datasource)를 주입받습니다.
   // D.I(의존성 주입) / IoC(제어의 역전) - 개발자가 new 안함
   @Autowired
   private DataSource dataSource;
   
    // 우리가 직접 만든 사용자 정보 조회 서비스(UserDetailsService 구현체)를 주입받습니다.
   
   // 1. 스프링 시큐리티 기능 완전 비활성화 (정적 리소스 용)
   /*
    * 보안 검사(인증, 인가)를 아예 거치지 않도록 예외 처리하는 설정입니다.
    * CSS, JS, 이미지 같은 정적 리소스들은 굳이 로그인 검사를 할 필요가 없으므로 
    * 시큐리티 필터를 타지 않게 하여 서버의 성능을 높입니다.
    */
   // 스프링 부트 3.x에서는 WebSecurityCustomizer를 Bean으로 등록하여 사용합니다.
   // /static/ 하위의 모든 파일은 보안 검사 무시!
   

   // 2. 특정 HTTP 요청에 대한 웹 기반 보안 구성 (핵심 필터 체인)
   /*
    * 어떤 URL로 들어올 때 로그인을 요구할지, 어떤 권한이 필요한지, 
    * 로그인/로그아웃 처리는 어떻게 할지 촘촘하게 설정하는 가장 중요한 메서드입니다.
    */
   
            // 3. CSRF 및 기본 인증 비활성화
            // CSRF(크로스 사이트 요청 위조) 보호를 비활성화합니다. (실무 REST API 환경에서는 주로 끄고, 일반 웹에서는 켭니다.)
    
            // HTTP Basic 인증(브라우저가 띄우는 기본 로그인 창)을 비활성화하고, 우리가 만든 폼 로그인을 쓸 것입니다.
   
            
            // 4. Clickjacking 방지 (Iframe 허용 설정)
            // h2-console이나 iframe을 화면에 띄우기 위해, 같은 도메인(Origin) 내에서는 frame(창) 사용을 허가합니다.
   
            
            // 5. URL별 권한(인가) 설정
   
                  // JSP 페이지 이동(FORWARD)이나 비동기(ASYNC) 요청은 권한 검사 없이 모두 허가합니다.
    
                  // 아래 나열된 URL들은 로그인 없이 누구나(permitAll) 접근할 수 있습니다. (로그인, 회원가입, 에러페이지, 관리자테마 등)
   
                  // /ceo/ 로 시작하는 모든 경로는 "CEO" 권한을 가진 사람만 들어갈 수 있습니다.
   
                  // /manager/ 로 시작하는 모든 경로는 "CEO" 또는 "MANAGER" 권한 중 하나라도 있어야 들어갈 수 있습니다.
   
                  // 위에서 설정한 경로들을 제외한 '나머지 모든 요청(anyRequest)'은 무조건 '로그인(인증)을 해야만(authenticated)' 접근 가능합니다.
   
   
            
            // 6. 폼 로그인 설정
   
                  // 우리가 직접 만든 커스텀 로그인 페이지의 URL을 지정합니다. (이걸 안 쓰면 스프링이 제공하는 못생긴 기본 창이 뜹니다.)
   
                  // 로그인이 성공적으로 완료되었을 때 자동으로 이동할 기본 페이지 경로입니다.
   
   
            
            // 7. 세션 관리 설정 (동시 접속 제어)
   
                  // 하나의 아이디로 동시에 접속할 수 있는 최대 세션(기기) 수를 1개로 제한합니다. (중복 로그인 방지)
   
            
            // 8. 로그아웃 설정
   
                  // 로그아웃이 성공하면 /login 경로로 이동시킵니다.
   
                  // 로그아웃 시 현재 사용자의 세션 정보를 서버에서 완전히 날려버립니다.(무효화)
   
   // 지금까지의 설정을 조립해서 SecurityFilterChain 객체를 반환합니다.
   
   
   // 9. 인증 관리자(AuthenticationManager) 관련 설정
   /*
    * "사용자가 입력한 아이디/비밀번호"와 "DB에 저장된 아이디/비밀번호"가 일치하는지 
    * 실질적으로 검사(인증)하는 총괄 매니저를 세팅합니다.
    */
   
      
      // DB 기반으로 인증을 처리할 Provider 객체를 만듭니다.
   
      
      // 10. 사용자 정보 서비스 및 비밀번호 암호화 인코더 설정
      /*
       * 총괄 매니저에게 "사용자 정보는 이 서비스(userServiceImpl)한테 물어보고, 
       * 비밀번호 맞는지 비교할 때는 이 암호화 기계(bCryptPasswordEncoder)를 써라!" 라고 지시합니다.
       */
   
      
   
   // 11. 패스워드 인코더(비밀번호 암호화) 빈 등록
   /*
    * 회원가입 시 비밀번호를 암호화해서 DB에 저장하고, 로그인 시 입력한 비밀번호를 
    * 동일한 방식으로 암호화하여 DB의 값과 비교할 때 사용되는 BCrypt 해시 함수입니다.
    */
   
}
