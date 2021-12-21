package ua.com.alevel.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.cron.model.BookSupplier;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.repository.book.BookRepository;
import ua.com.alevel.service.SupplierService;

import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Value("${supplier.url}")
    private String url;

    @Value("${supplier.token}")
    private String token;

    private final BookRepository bookRepository;

    public SupplierServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void syncToSupplier() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x_auth_token", token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<BookSupplier[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                BookSupplier[].class
        );

        if (response.getStatusCodeValue() == 200) {
            BookSupplier[] bookSuppliers = response.getBody();
            if (bookSuppliers != null) {
                for (BookSupplier bookSupplier : bookSuppliers) {
                    Optional<Book> bookOptional = bookRepository.findById(bookSupplier.getBookId());
                    if (bookOptional.isPresent()) {
                        Book book = bookOptional.get();
                        book.setQuantity(bookSupplier.getQuantity());
                        book.setPrice(bookSupplier.getPrice());
                        bookRepository.save(book);
                    }
                }
            }
        }
    }
}
