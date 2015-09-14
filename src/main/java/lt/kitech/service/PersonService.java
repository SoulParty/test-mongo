package lt.kitech.service;

import lt.kitech.model.Address;
import lt.kitech.model.Person;

import java.util.List;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
public interface PersonService {
    Person create(String name, String lastName, int age, Address address);
    List<Person> findPeople(String name, String street);
}
