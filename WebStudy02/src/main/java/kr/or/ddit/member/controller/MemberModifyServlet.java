package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;

//  오라클 전용 유틸리티보다는 Apache Commons Lang3나 직접 만든 유틸리티를 권장한다.
// import oracle.jdbc.driver.utils.StringUtils; 
import org.apache.commons.lang3.StringUtils;

@WebServlet("/member/modify")
public class MemberModifyServlet extends HttpServlet {

    // 서비스와 뷰 리졸버 객체가 선언되어 있어야 한다. (이미 상단이나 생성자에 있다고 가정한다.)
    private ViewResolver viewResolver = new ViewResolverComposite();
    private MemberService service = new MemberServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 로그인한 사용자 정보 조회
        String memId = req.getUserPrincipal().getName();

        // 회원 정보 조회 (Service에 메서드가 있는 경우)
        MemberDto member = service.readMember(memId);
        req.setAttribute("member", member);

        // modifyForm.jsp 화면 전화
        String lvn = "member/modifyForm";
        viewResolver.resolveViewName(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 16개 파라미터 수신 -> command Object 로 바인드

        Map<String, String[]> parameterMap = req.getParameterMap();
        MemberDto commandObject = PopulateUtils.populate(parameterMap, MemberDto.class);
        // 현재 로그인한 사용자의 ID를 커맨드 객체에 세팅한다.
        commandObject.setMemId(req.getUserPrincipal().getName());
        req.setAttribute("member", commandObject);

        // 2. command object 검증
        Map<String, List<String>> errors = ValidateUtils.validate(commandObject, UpdateGroup.class);
        req.setAttribute("errors", errors);

        String lvn = null;
        // 3. 검증 통과
        if (errors.isEmpty()) {
            try {
                service.modifyMember(commandObject);
                // 3-1. 마이 페이지로 이동: redirect
                lvn = "redirect:/member/mypage";
            } catch (Exception e) {
                // AuthenticationException이 별도로 정의되어 있지 않다면 일반 Exception으로 처리하거나
                // 해당 예외 클래스를 import 해야 한다.
                req.setAttribute("message", "비밀번호 오류");
                lvn = "member/modifyForm";
            }
        } else {
            // 4. 검증에 통과하지 못함
            // modifyForm 이동 : forward
            lvn = "member/modifyForm";
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }

    // 입력 데이터의 유효성을 체크하는 메서드이다.
    // private void validate(MemberDto commandObject, Map<String, List<String>> errors) {
    //     // if (StringUtils.isBlank(commandObject.getMemId())) {
    //     //     errors.put("memId", List.of("아이디는 필수 입력"));
    //     // }
    //     if (StringUtils.isBlank(commandObject.getMemPass())) {
    //         errors.put("memPass", List.of("비밀번호는 필수 입력"));
    //     }
    //     if (StringUtils.isBlank(commandObject.getMemName())) {
    //         errors.put("memName", List.of("이름 필수 입력"));
    //     }
    //     if (StringUtils.isBlank(commandObject.getMemZip())) {
    //         errors.put("memZip", List.of("우편번호 필수 입력"));
    //     }
    //     if (StringUtils.isBlank(commandObject.getMemAdd1())) {
    //         errors.put("memAdd1", List.of("상위 주소 필수 입력"));
    //     }
    //     if (StringUtils.isBlank(commandObject.getMemAdd2())) {
    //         errors.put("memAdd2", List.of("하위 주소 필수 입력"));
    //     }
    //     if (StringUtils.isBlank(commandObject.getMemMail())) {
    //         errors.put("memMail", List.of("이메일 필수 입력"));
    //     }
    // }
}