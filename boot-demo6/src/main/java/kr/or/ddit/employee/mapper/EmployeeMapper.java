package kr.or.ddit.employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.EmployeeDto;

@Mapper
public interface EmployeeMapper {
    /**
     * 직원 등록
     * @param employeeDto
     * @return
     */
    int insertEmployee(EmployeeDto employee);

    /**
     * 직원 목록 조회
     * @param employeeDto
     * @return
     */
    List<EmployeeDto> selectEmployeeList();

    /**
     * 직원 상세 조회
     * @param id
     * @return
     */
    EmployeeDto selectEmployee(@Param("employeeId")int employeeId);

    /**
     * 직원 수정
     * @param employeeDto
     * @return
     */
    int updateEmployee(EmployeeDto employee);

    /**
     * 직원 삭제
     * @param id
     * @return
     */
    int deleteEmployee(@Param("employeeId")int employeeId);
}
