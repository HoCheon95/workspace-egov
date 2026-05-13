package kr.or.ddit.employee.mapper;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    EmployeeMapper employeeMapper;
    EmployeeDto employeeDto;

    @BeforeEach
    void setup() {
        employeeDto = EmployeeDto.builder()
                .firstName("dummyfn")
                .lastName("dummyln")
                .empName("dummyfndummyln")
                .email("dummy")
                .hireDate(LocalDate.now())
                .jobId("IT_PROG")
                .departmentId(60)
                .managerId(103)
                .salary(4800)
                .build();
        employeeMapper.insertEmployee(employeeDto);
   
    }

    @Test
    void testSelectEmployeeList() {
        log.info("employeeList : {}", employeeMapper.selectEmployeeList());
    }
}
