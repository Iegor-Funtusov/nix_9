package ua.com.alevel.cron;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.cron.model.BookSupplier;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.repository.book.BookRepository;

import java.util.Optional;

@Service
public class SupplierCronJob {

    private final BookRepository bookRepository;

    public SupplierCronJob(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    @Scheduled(fixedDelay = 1000 * 60)
    public void syncToSupplier() {
        System.out.println("SupplierCronJob.syncToSupplier");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token", "hello");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<BookSupplier[]> response = restTemplate.exchange(
                "http://localhost:8081/api/books",
                HttpMethod.GET,
                request,
                BookSupplier[].class
        );

        if (response.getStatusCodeValue() == 200) {
            BookSupplier[] bookSuppliers = response.getBody();
            for (BookSupplier bookSupplier : bookSuppliers) {
                Optional<Book> bookOptional = bookRepository.findById(bookSupplier.getBookId());
                if (bookOptional.isPresent()) {
                    Book book = bookOptional.get();
                    book.setPrice(bookSupplier.getPrice());
                    book.setQuantity(bookSupplier.getQuantity());
                    bookRepository.save(book);
                }
            }
        }
    }
}
