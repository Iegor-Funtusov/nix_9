package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.RequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ResponseDto;

public interface BaseFacade<REQ extends RequestDto, SIMPLE_RES extends ResponseDto, FULL_RES extends SIMPLE_RES> {

    void create(REQ req);
    void update(REQ req, long id);
    void delete(long id);
    FULL_RES findById(long id);
    PageData<SIMPLE_RES> findAll(WebRequest request);
}
