package lt.kitech.model;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@Embedded
public class Address {

    private String street;
    private String city;
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
// ... optional getters and setters
}
