package ua.com.alevel.facade;

import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.dto.employee.EmployeeResponseDto;

import java.util.List;

public interface EmployeeFacade extends BaseFacade<EmployeeRequestDto, EmployeeResponseDto> {

    List<EmployeeResponseDto> findAllByDepartment(Long departmentId);
}
