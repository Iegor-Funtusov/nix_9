package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Author;

public class AuthorResponseDto extends DtoResponse {

    private String firstName;
    private String lastName;
    private Integer bookCount;

    public AuthorResponseDto() { }

    public AuthorResponseDto(Author author) {
        setId(author.getId());
        setCreated(author.getCreated());
        setUpdated(author.getUpdated());
        setVisible(author.getVisible());
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "AuthorResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bookCount=" + bookCount +
                '}';
    }
}
