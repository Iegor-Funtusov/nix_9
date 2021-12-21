package ua.com.alevel.persistence.repository.book;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.publisher.Publisher;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface PublisherRepository extends BaseRepository<Publisher> { }
