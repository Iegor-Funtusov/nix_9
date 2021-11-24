package ua.com.alevel.view.dto.request;

import com.neovisionaries.i18n.CountryCode;

public class PublisherRequestDto extends DtoRequest {

    private String name;
    private CountryCode country;

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
