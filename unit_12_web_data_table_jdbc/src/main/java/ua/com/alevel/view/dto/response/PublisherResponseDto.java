package ua.com.alevel.view.dto.response;

import com.neovisionaries.i18n.CountryCode;
import ua.com.alevel.persistence.entity.Publisher;

public class PublisherResponseDto extends DtoResponse {

    private String name;
    private CountryCode country;

    public PublisherResponseDto() { }

    public PublisherResponseDto(Publisher publisher) {
        setId(publisher.getId());
        setCreated(publisher.getCreated());
        setUpdated(publisher.getUpdated());
        setVisible(publisher.getVisible());
        this.name = publisher.getName();
        this.country = publisher.getCountry();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }
}
