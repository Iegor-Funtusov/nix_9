package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.DepartmentRequestDto;
import ua.com.alevel.view.dto.response.DepartmentFullResponseDto;
import ua.com.alevel.view.dto.response.DepartmentSimpleResponseDto;

public interface DepartmentFacade extends BaseFacade<DepartmentRequestDto, DepartmentSimpleResponseDto, DepartmentFullResponseDto> { }
