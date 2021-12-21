package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.response.BookPLPDto;

import java.util.List;

public interface PLPFacade {

    List<BookPLPDto> search(WebRequest webRequest);
}
