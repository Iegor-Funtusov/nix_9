package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.request.RequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.ResponseDto;

public interface CrudFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    PageData<RES> findAll(WebRequest request);
}
