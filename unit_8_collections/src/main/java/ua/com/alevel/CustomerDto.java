package ua.com.alevel;

public class CustomerDto {

    private Long id;
    private String fullName;
    private Long created;

    public CustomerDto() { }

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.created = customer.getCreated().getTime();
        this.fullName = customer.getFirstName() + " " + customer.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", created=" + created +
                '}';
    }
}
