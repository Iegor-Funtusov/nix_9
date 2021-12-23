package ua.com.alevel.web.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.book.Genre;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class BookPLPDto {

    private Long id;
    private Boolean visible;
    private String bookName;
    private String imageUrl;
    private BigDecimal actualPrice;
    private BigDecimal price;
    private Set<String> authors;
    private Set<String> genres;
    private Publisher publisher;

    public BookPLPDto(Book book) {
        this.id = book.getId();
        this.visible = book.getVisible();
        this.bookName = book.getBookName();
        this.imageUrl = book.getImageUrl();
        this.price = book.getPrice();
        this.actualPrice = book.getPrice();
        if (CollectionUtils.isNotEmpty(book.getAuthors())) {
            this.authors = book.getAuthors()
                    .stream()
                    .map(author -> author.getFirstName() + " " + author.getLastName())
                    .collect(Collectors.toSet());
        }
        if (CollectionUtils.isNotEmpty(book.getGenres())) {
            this.genres = book.getGenres()
                    .stream()
                    .map(Genre::getType)
                    .collect(Collectors.toSet());
        }
        if (book.getPublisher() != null) {
            this.publisher = new Publisher(
                    book.getPublisher().getId(),
                    book.getPublisher().getName());
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

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    private static final class Publisher {

        private final Long id;
        private final String name;

        private Publisher(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Long id() {
            return id;
        }

        public String name() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Publisher) obj;
            return Objects.equals(this.id, that.id) &&
                    Objects.equals(this.name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "Publisher[" +
                    "id=" + id + ", " +
                    "name=" + name + ']';
        }

    }
}
