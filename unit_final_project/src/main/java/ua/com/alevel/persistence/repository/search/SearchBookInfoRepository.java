package ua.com.alevel.persistence.repository.search;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.search.SearchBookInfo;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface SearchBookInfoRepository extends BaseRepository<SearchBookInfo> {

    SearchBookInfo findByPublisher(String publisher);
}
