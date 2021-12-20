package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> { }
