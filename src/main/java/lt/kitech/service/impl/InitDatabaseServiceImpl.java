package lt.kitech.service.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import lt.kitech.model.Person;
import lt.kitech.service.AddressService;
import lt.kitech.service.InitDatabaseService;
import lt.kitech.service.PersonService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@Service
@PropertySource(value = "classpath:application.properties")
public class InitDatabaseServiceImpl implements InitDatabaseService {

    private static final Logger log = Logger.getLogger(InitDatabaseServiceImpl.class);

    @Value("${mongodb.name}")
    private String DB_NAME;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonService personService;

    @Autowired
    MongoClient mongo;

    @Override
    public void createPeople(int numberOfPeople) {
        if (mongo != null) {
            DB db = mongo.getDB(DB_NAME);
            if (db.collectionExists(DB_NAME)) {
                DBCollection myCollection = db.getCollection("Person");
                myCollection.drop();
            }
            for (int i = 0; i < numberOfPeople; i++) {
                Person person = personService.create(
                        "Person" + i,
                        "Person" + i,
                        (int) (Math.random() * 100),
                        addressService.create("City" + i, "Country" + i, "Street " + (i % 2)));
                log.info(person.getName() + " created succesfully");
            }
        }
    }
}
