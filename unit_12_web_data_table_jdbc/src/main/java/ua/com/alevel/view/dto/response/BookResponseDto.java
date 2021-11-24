package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Book;

public class BookResponseDto extends DtoResponse {

    private String bookName;
    private String imageUrl;
    private Integer pageSize;
    private Integer publicationDate;
    private String description;

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
}
