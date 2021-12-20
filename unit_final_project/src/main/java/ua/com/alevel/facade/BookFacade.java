package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.BookRequestDto;
import ua.com.alevel.web.dto.response.BookResponseDto;

public interface BookFacade extends CrudFacade<BookRequestDto, BookResponseDto> { }
