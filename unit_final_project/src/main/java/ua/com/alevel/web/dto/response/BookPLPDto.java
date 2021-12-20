package ua.com.alevel.web.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.book.Book;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class BookPLPDto {

    private Long id;
    private Boolean visible;
    private String bookName;
    private String imageUrl;
    private BigDecimal price;
    private Set<String> authors;

    public BookPLPDto(Book book) {
        this.id = book.getId();
        this.visible = book.getQuantity() != null && book.getQuantity() != 0;
        this.bookName = book.getBookName();
        this.imageUrl = book.getImageUrl();
        this.price = book.getPrice();
        if (CollectionUtils.isNotEmpty(book.getAuthors())) {
            this.authors = book.getAuthors()
                    .stream()
                    .map(author -> author.getFirstName() + " " + author.getLastName())
                    .collect(Collectors.toSet());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }
}
