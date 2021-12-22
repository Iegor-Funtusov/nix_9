package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.publisher.Publisher;
import ua.com.alevel.persistence.entity.search.SearchBookInfo;
import ua.com.alevel.persistence.repository.book.PublisherRepository;
import ua.com.alevel.persistence.repository.search.SearchBookInfoRepository;
import ua.com.alevel.service.SearchBookInfoService;
import ua.com.alevel.util.WebUtil;

import java.util.Optional;

@Service
public class SearchBookInfoServiceImpl implements SearchBookInfoService {

    private final PublisherRepository publisherRepository;
    private final SearchBookInfoRepository searchBookInfoRepository;

    public SearchBookInfoServiceImpl(PublisherRepository publisherRepository, SearchBookInfoRepository searchBookInfoRepository) {
        this.publisherRepository = publisherRepository;
        this.searchBookInfoRepository = searchBookInfoRepository;
    }

    @Override
    public void process(String searchParam, Long id) {
        if (searchParam.equals(WebUtil.PUBLISHER_PARAM)) {
            Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
            if (optionalPublisher.isPresent()) {
                SearchBookInfo searchBookInfo = searchBookInfoRepository.findByPublisher(optionalPublisher.get().getName());
                if (searchBookInfo == null) {
                    searchBookInfo = new SearchBookInfo();
                    searchBookInfo.setPublisher(optionalPublisher.get().getName());
                    searchBookInfo.setCountPublisher(1L);
                } else {
                    Long count = searchBookInfo.getCountPublisher();
                    ++count;
                    searchBookInfo.setCountPublisher(count);
                }
                searchBookInfoRepository.save(searchBookInfo);
            }
        }
    }
}
