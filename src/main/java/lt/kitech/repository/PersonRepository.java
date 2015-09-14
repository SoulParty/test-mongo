package lt.kitech.repository;

import lt.kitech.model.Person;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

/**
 * Created by Danielius Kibartas on 2015-09-11.
 */
public interface PersonRepository extends DAO<Person, ObjectId> {
    List<Person> findByName(String name);
    List<Person> findByStreet(String street);
    List<Person> findByNameAndStreet(String name, String street);
}
