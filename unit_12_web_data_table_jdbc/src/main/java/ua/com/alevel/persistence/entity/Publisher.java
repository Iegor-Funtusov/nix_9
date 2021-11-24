package ua.com.alevel.persistence.entity;

import com.neovisionaries.i18n.CountryCode;

public class Publisher extends BaseEntity {

    private String name;
    private CountryCode country;

    public Publisher() {
        super();
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
