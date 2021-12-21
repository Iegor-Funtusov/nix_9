package ua.com.alevel.persistence.repository.book;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.author.Author;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface AuthorRepository extends BaseRepository<Author> { }
