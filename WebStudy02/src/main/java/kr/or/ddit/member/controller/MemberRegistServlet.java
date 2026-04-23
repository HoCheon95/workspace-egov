package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;

@WebServlet("/member/regist")
public class MemberRegistServlet extends HttpServlet {
    private ViewResolver viewResolver = new ViewResolverComposite();
    private MemberService service = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lvn = "member/registForm";
        viewResolver.resolveViewName(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 1. 17개 파라미터 수신 -> command Object 로 바인드

        Map<String, String[]> parameterMap = req.getParameterMap();
        MemberDto commandObject = PopulateUtils.populate(parameterMap, MemberDto.class);
        req.setAttribute("member", commandObject);

        // 2. command object 검증
        Map<String, List<String>> errors =  ValidateUtils.validate(commandObject, InsertGroup.class);
        req.setAttribute("errors", errors);

        String lvn = null;
        // 3. 검증 통과
        if (errors.isEmpty()) {
            service.registMember(commandObject);
            // 3-1. 웰컴 페이지로 이동: redirect
            lvn = "redirect:/";
        } else {
            // 4. 검증에 통과하지 못함
            // registForm 이동 : forward
            lvn = "member/registForm";
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }

    // private void validate(MemberDto commandObject, Map<String, List<String>> errors) {
    //     if (StringUtils.isBlank(commandObject.getMemId())) {
    //         errors.put("memId", List.of("아이디 필수 입력"));
    //     }
    //     if (StringUtils.isBlank(commandObject.getMemPass())) {
    //         errors.put("memPass", List.of("비밀번호 필수 입력"));
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
