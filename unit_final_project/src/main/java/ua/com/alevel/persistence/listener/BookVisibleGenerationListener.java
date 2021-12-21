package ua.com.alevel.persistence.listener;

import ua.com.alevel.persistence.entity.book.Book;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.math.BigDecimal;

public class BookVisibleGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateBookVisible(Book book) {
        book.setVisible(book.getQuantity() != null &&
                book.getQuantity() > 0 &&
                book.getPrice() != null &&
                book.getPrice().compareTo(new BigDecimal("00.00")) > 0);
    }
}
