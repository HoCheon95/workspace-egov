package kr.or.ddit.member.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.renderer.DefaultPaginationRenderer;
import kr.or.ddit.common.paging.renderer.PaginationRenderer;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/memberList")
public class MemberReadController {

    @Autowired
    private MemberService service;

    @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PaginationInfo<MemberDTO> listToJson(
            @RequestParam(value = "page", defaultValue = "1", required = false) int currentPage
            ) {
        PaginationInfo<MemberDTO> paginationInfo = new PaginationInfo<>(5, 5);
        paginationInfo.setCurrentPage(currentPage);

        List<MemberDTO> memberList = service.readMemberList(paginationInfo);
        paginationInfo.setDataList(memberList);

        return paginationInfo;
    }

    @GetMapping(value = "type-async", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> typeAsyncData(
        @RequestParam(value = "page", defaultValue = "1", required = false) int currentPage,
        @ModelAttribute("detailSearch") MemberDTO detailCondition
    ) {
        PaginationInfo<MemberDTO> paginationInfo = new PaginationInfo<>(5, 5);
        paginationInfo.setDetailCondition(detailCondition);
        paginationInfo.setCurrentPage(currentPage);

        List<MemberDTO> memberList = service.readMemberList(paginationInfo);

        PaginationRenderer renderer = new DefaultPaginationRenderer();
        String pagingHtml = renderer.renderPagination(paginationInfo, "fnPaging");

        return Map.of("dataList", memberList, "pagingHtml", pagingHtml);
    }

    @GetMapping("type-async")
    public String typeAsyncUi() {
        return "member/memberList-async";
    }

    @GetMapping(produces = org.springframework.http.MediaType.TEXT_HTML_VALUE)
    public String viewMemberList(Model model,
        @RequestParam(value = "page", defaultValue = "1", required = false) int currentPage,
        @ModelAttribute("detailSearch") MemberDTO detailCondition
    ) {
        PaginationInfo<MemberDTO> paginationInfo = new PaginationInfo<>(5, 5);
        paginationInfo.setDetailCondition(detailCondition);
        paginationInfo.setCurrentPage(currentPage);
        List<MemberDTO> memberList = service.readMemberList(paginationInfo);
        model.addAttribute("memberList", memberList);
        
        log.info("memberlist 확인 : {}",paginationInfo.getSimpleCondition());
        PaginationRenderer renderer = new DefaultPaginationRenderer();
        String pagingHtml = renderer.renderPagination(paginationInfo, "fnPaging");
        model.addAttribute("pagingHtml", pagingHtml);

        return "member/memberList";
    }
}
