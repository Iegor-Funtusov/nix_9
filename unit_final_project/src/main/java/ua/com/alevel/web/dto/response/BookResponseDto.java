package ua.com.alevel.web.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.author.Author;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.book.Genre;
import ua.com.alevel.persistence.entity.publisher.Publisher;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class BookResponseDto extends ResponseDto {

    private String bookName;
    private String imageUrl;
    private Integer pageSize;
    private Integer publicationDate;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Set<Author> authors = Collections.emptySet();
    private Set<Genre> genres = Collections.emptySet();
    private Publisher publisher;

    public BookResponseDto() { }

    public BookResponseDto(Book book) {
        setId(book.getId());
        setCreated(book.getCreated());
        setUpdated(book.getUpdated());
        setVisible(book.getVisible());
        this.bookName = book.getBookName();
        this.imageUrl = book.getImageUrl();
        this.pageSize = book.getPageSize();
        this.publicationDate = book.getPublicationDate();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.quantity = book.getQuantity();
        if (book.getPublisher() != null) {
            this.publisher = book.getPublisher();
        }
        if (CollectionUtils.isNotEmpty(book.getAuthors())) {
            this.authors = book.getAuthors();
        }
        if (CollectionUtils.isNotEmpty(book.getGenres())) {
            this.genres = book.getGenres();
        }
    }

    public String getBookName() {
        return bookName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
