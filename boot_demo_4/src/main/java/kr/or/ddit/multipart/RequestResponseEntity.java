package kr.or.ddit.multipart;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.model.Model;
import kr.or.ddit.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class RequestResponseEntity {

    @GetMapping("/send-response1")
    public void case1() {}
    
    @GetMapping("/send-response2")
    public String case2(Model model) {
        return "send-response2";
    }

    @GetMapping(value = "/send-response3", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, ?> case3() {
        return Map.of();
    }

    @GetMapping(value = "/send-response4", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, ?>> case4() {
        // 1. 상태코드 결정 : 200
        // 2. response header 결정 : Content-type, Content-length
        // 3. response body 결정 : json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.setContentLength(100);
        return ResponseEntity.ok()
                    .headers(headers)
                    .body(Map.of("prop1", "value1", "prop2", 34));
    }
    

    @GetMapping("/receive-case1")
    public void receiveCase1(RequestEntity<?> req, @RequestHeader String accept) {
        log.info("{}", req);
        URI url = req.getUrl();
        HttpMethod method = req.getMethod();
        HttpHeaders headers = req.getHeaders();
        List<MediaType> accepts = headers.getAccept();

    }
    
    @PostMapping("/receive-case2")
    public void receiveCase2(RequestEntity<MemberDTO> req) {
        log.info("{}", req);
        HttpHeaders headers = req.getHeaders();
        MediaType contentType = headers.getContentType();
        MemberDTO body = req.getBody();
    }

    @PostMapping(value = "/receive-case3", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receiveCase3(@RequestBody MemberDTO body) {
    }
}
