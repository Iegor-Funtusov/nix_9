package ua.com.alevel.persistence.entity.search;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "search_book_infos")
public class SearchBookInfo extends BaseEntity {

    @Column(name = "count_publisher")
    private Long countPublisher;

    @Column(name = "count_author")
    private Long countAuthor;

    @Column(name = "count_book_name")
    private Long countBookName;

    @Column(unique = true)
    private String publisher;

    @Column(unique = true)
    private String author;

    @Column(unique = true)
    private String bookName;

    public SearchBookInfo() {
        super();
        this.countPublisher = 0L;
        this.countAuthor = 0L;
        this.countBookName = 0L;
    }

    public Long getCountPublisher() {
        return countPublisher;
    }

    public void setCountPublisher(Long countPublisher) {
        this.countPublisher = countPublisher;
    }

    public Long getCountAuthor() {
        return countAuthor;
    }

    public void setCountAuthor(Long countAuthor) {
        this.countAuthor = countAuthor;
    }

    public Long getCountBookName() {
        return countBookName;
    }

    public void setCountBookName(Long countBookName) {
        this.countBookName = countBookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
