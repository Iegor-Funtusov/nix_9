package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.EmployeeRequestDto;
import ua.com.alevel.view.dto.response.EmployeeFullResponseDto;
import ua.com.alevel.view.dto.response.EmployeeSimpleResponseDto;

public interface EmployeeFacade extends BaseFacade<EmployeeRequestDto, EmployeeSimpleResponseDto, EmployeeFullResponseDto> { }
