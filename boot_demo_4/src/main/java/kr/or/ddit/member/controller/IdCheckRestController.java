package kr.or.ddit.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.member.service.MemberService;


@RestController
public class IdCheckRestController {
    @Autowired
    private MemberService service;

    @PostMapping("/member/idCheck")
    public Map<String, Object> idCheck(@RequestParam String checkId) {
        boolean duplicated = false;
        try{
            service.readMember(checkId);
            duplicated = true;
        } catch (EntityNotFoundException e) {
            duplicated = false;
        }
        return Map.of("duplicated", duplicated);
    }
}
