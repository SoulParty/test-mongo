package lt.kitech.service;

import lt.kitech.model.Address;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
public interface AddressService {
    Address create(String country, String city, String street);
}
