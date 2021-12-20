package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.entity.Book;
import ua.com.alevel.repository.BookRepository;

import java.math.BigDecimal;
import java.util.Random;

@EnableJpaRepositories(basePackages = "ua.com.alevel.repository")
@SpringBootApplication
public class UnitFinalProjectSupplierApplication {

    private final BookRepository bookRepository;

    public UnitFinalProjectSupplierApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnitFinalProjectSupplierApplication.class, args);
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
//        for (int i = 0; i < 7; i++) {
//            Book book = new Book();
//            long bookId = i + 1;
//            book.setBookId(bookId);
//            book.setPrice(new BigDecimal("250.00"));
//            book.setQuantity(i);
//            bookRepository.save(book);
//        }
    }
}
