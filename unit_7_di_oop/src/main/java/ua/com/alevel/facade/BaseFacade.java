package ua.com.alevel.facade;

import ua.com.alevel.dto.BaseRequestDto;
import ua.com.alevel.dto.BaseResponseDto;

import java.util.Collection;

public interface BaseFacade<REQUEST_DTO extends BaseRequestDto, RESPONSE_DTO extends BaseResponseDto> {

    void create(REQUEST_DTO requestDto);
    void update(REQUEST_DTO requestDto, Integer id);
    void delete(Integer id);
    RESPONSE_DTO findById(Integer id);
    Collection<RESPONSE_DTO> findAll();
}
