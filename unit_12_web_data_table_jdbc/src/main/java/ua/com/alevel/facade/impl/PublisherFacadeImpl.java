package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PublisherFacade;
import ua.com.alevel.service.PublisherService;
import ua.com.alevel.view.dto.request.PublisherRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PublisherResponseDto;

@Service
public class PublisherFacadeImpl implements PublisherFacade {

    private final PublisherService publisherService;

    public PublisherFacadeImpl(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Override
    public void create(PublisherRequestDto publisherRequestDto) {

    }

    @Override
    public void update(PublisherRequestDto publisherRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PublisherResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<PublisherResponseDto> findAll(WebRequest request) {
        return null;
    }
}
