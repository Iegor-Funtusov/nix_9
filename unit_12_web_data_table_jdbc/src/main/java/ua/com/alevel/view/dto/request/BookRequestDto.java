package ua.com.alevel.view.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class BookRequestDto extends DtoRequest {

    private String bookName;
    private Integer pageSize;
    private Integer publicationDate;
    private String description;
    private MultipartFile bookImage;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getBookImage() {
        return bookImage;
    }

    public void setBookImage(MultipartFile bookImage) {
        this.bookImage = bookImage;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "BookRequestDto{" +
                "bookName='" + bookName + '\'' +
                ", pageSize=" + pageSize +
                ", description='" + description + '\'' +
                ", bookImage='" + bookImage.getOriginalFilename() + '\'' +
                '}';
    }
}
