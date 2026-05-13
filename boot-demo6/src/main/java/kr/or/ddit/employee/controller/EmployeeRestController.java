package kr.or.ddit.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.dto.EmployeeDto;
import kr.or.ddit.employee.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * RestFul URI (명사-URL + 동사-METHOD)
 *      /rest/employees GET
 *      /rest/employees/100 GET
 *      /rest/employees POST
 *      /rest/employees/100 PUT
 *      /rest/employees/100 DELETE
 */
@Slf4j
@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 직원 목록 조회
     * @param param
     * @return
     */
    @GetMapping
    public List<EmployeeDto> list(Authentication authentication) {
        Jwt jwt =  (Jwt) authentication.getPrincipal();
        String memName = jwt.getClaim("name");
        log.info("======> {}, {}", authentication.getName(), memName);
        log.info("======> {}", authentication.getAuthorities());
        return employeeMapper.selectEmployeeList();
    }

    /**
     * 직원 상세 조회
     * @param employeeId
     * @return
     */
    @GetMapping("/{employeeId}")
    public EmployeeDto detail(@PathVariable int employeeId) {
        return employeeMapper.selectEmployee(employeeId);
    }

    /**
     * 직원 등록
     * @param employeeDto
     * @return
     */
    @PostMapping
    public Map<String, ?> create(@RequestBody EmployeeDto employeeDto) {
        int rowcnt = employeeMapper.insertEmployee(employeeDto);
        return Map.of("success", rowcnt > 0, "target", employeeDto);
    }

    /**
     * 직원 수정
     * @param employeeId
     * @param entity
     * @return
     */
    @PutMapping("/{employeeId}")
    public Map<String, ?> modify(@PathVariable int employeeId, @RequestBody EmployeeDto employeeDto) {
        employeeDto.setEmployeeId(employeeId);

        int rowcnt = employeeMapper.updateEmployee(employeeDto);
        return Map.of("success", rowcnt > 0, "target", employeeDto);
    }

    /**
     * 직원 삭제
     * @param employeeId
     * @return
     */
    @DeleteMapping("/{employeeId}")
    public Map<String, ?> delete(@PathVariable int employeeId) {
        EmployeeDto employeeDto = employeeMapper.selectEmployee(employeeId);
        employeeMapper.deleteEmployee(employeeId);
        return Map.of("success", employeeDto != null, "target", employeeDto);
    }
    
    
    
}
