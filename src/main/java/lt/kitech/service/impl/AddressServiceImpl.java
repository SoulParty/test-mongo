package lt.kitech.service.impl;

import lt.kitech.model.Address;
import lt.kitech.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Override
    public Address create(String country, String city, String street) {
        Address address = new Address();
        address.setCity(city);
        address.setCountry(country);
        address.setStreet(street);
        return address;
    }
}
