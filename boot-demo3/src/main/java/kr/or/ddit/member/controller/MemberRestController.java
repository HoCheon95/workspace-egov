package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.dto.MemberDto;
import kr.or.ddit.member.service.MemberService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Request line : URI(명사, 어떤 자원) Method(동사, 행위정보-CRUD)
 * /rest/members
 * C (POST)
 * R (GET)
 *      다건
 *      단건 : /rest/members/a001 (경로 변수)
 * U (PUT)
 * D (DELETE)
 */
@RestController // @Controller + @ResponseBody
@RequestMapping("/rest/members")
public class MemberRestController {
    @Autowired
    private MemberService service;

    @GetMapping 
    public List<MemberDto> memberListJson() {
        List<MemberDto> list = service.readMemberList();
        return list;
    }

    @GetMapping("{memId}") // 경로 변수명 (PathVariable)
    public MemberDto memberJson(@PathVariable String memId) {
        return null;
    }

    @PostMapping
    public Map<String, Object> memberInsert(@RequestBody MemberDto member) {
        return Map.of("success", true, "target", member);
    }
    
    @PutMapping("{memId}")
    public Map<String, Object> memberUpdate(@PathVariable String memId, @RequestBody MemberDto member) {
        member.setMemId(memId);
        return Map.of("success", true, "target", member);
    }

    @DeleteMapping("{memId}") // 경로 변수명 (PathVariable)
    public Map<String, Object> memberDelete(@PathVariable String memId) {
        return Map.of("success", true, "target", memId);
    }
}
