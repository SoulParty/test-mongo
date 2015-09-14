package lt.kitech.service.impl;

import lt.kitech.repository.PersonRepository;
import lt.kitech.model.Address;
import lt.kitech.model.Person;
import lt.kitech.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger log = Logger.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(String name, String lastName, int age, Address address) {
        Person person = new Person();

        person.setName(name);
        person.setLastName(lastName);
        person.setAge(age);
        person.setAddress(address);

        personRepository.save(person);
        return person;
    }

    @Override
    public List<Person> findPeople(String name, String street) {
        if (name != null) {
            return personRepository.findByNameAndStreet(name, street);
        } else if (street != null){
            return personRepository.findByStreet(street);
        } else {
            return new ArrayList<>();
        }
    }
}
