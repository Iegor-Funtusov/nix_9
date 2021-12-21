package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.response.BookPLPDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PLPFacadeImpl implements PLPFacade {

    private final PLPService plpService;

    public PLPFacadeImpl(PLPService plpService) {
        this.plpService = plpService;
    }

    @Override
    public List<BookPLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        if (webRequest.getParameterMap().get(WebUtil.PUBLISHER_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.PUBLISHER_PARAM);
            Long publisherId = Long.parseLong(params[0]);
            queryMap.put(WebUtil.PUBLISHER_PARAM, publisherId);
        }
        List<Book> books = plpService.search(queryMap);
        List<BookPLPDto> bookPLPDtos = books.stream().map(BookPLPDto::new).toList();
        return bookPLPDtos;
    }
}
