package ua.com.alevel.persistence.repository.book;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.book.Genre;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface GenreRepository extends BaseRepository<Genre> { }
