package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.book.Book;

import java.math.BigDecimal;

public class BookResponseDto extends ResponseDto {

    private String bookName;
    private String imageUrl;
    private Integer pageSize;
    private Integer publicationDate;
    private String description;
    private BigDecimal price;
    private Integer quantity;

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
}
