package lt.kitech.repository;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lt.kitech.config.MongoTestConfig;
import lt.kitech.config.MorphiaConfig;
import lt.kitech.config.MorphiaTestConfig;
import lt.kitech.model.Address;
import lt.kitech.model.Person;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import java.util.List;

/**
 * Created by Danielius Kibartas on 2015-09-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        PersonRepositoryTest.PersonRepositoryImplTestContextConfiguration.class,
        MongoTestConfig.class,
        MorphiaTestConfig.class})
public class PersonRepositoryTest {

    private static final MongodStarter starter = MongodStarter.getDefaultInstance();
    private static MongodExecutable mongodExe;
    private static MongodProcess mongod;

    @Configuration
    static class PersonRepositoryImplTestContextConfiguration {

        @Autowired
        Datastore datastore;

        @Bean
        public PersonRepository personRepository() {
            return new PersonRepositoryImpl(datastore);
        }
    }

    @Autowired
    private PersonRepository personRepository;

    @BeforeClass
    public static void setUp() throws Exception {
        mongodExe = starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(12345, Network.localhostIsIPv6()))
                .build());
        mongod = mongodExe.start();
    }

    @Test
    public void test01_insert() {
        Person person = new Person();
        person.setName("name");
        person.setLastName("last name");
        person.setAge(5);
        person.setAddress(null);

        personRepository.save(person);
    }

    @Test
    public void test02_findByName() {
        Person person = new Person();
        person.setName("name");
        person.setLastName("last name");
        person.setAge(5);
        person.setAddress(null);
        personRepository.save(person);
        List<Person> byName = personRepository.findByName(person.getName());
        assertTrue(byName != null && byName.size() > 0);
    }

    @Test
    public void test03_findByStreet() {
        Person person = new Person();
        person.setName("name");
        person.setLastName("last name");
        person.setAge(5);

        Address address = new Address();
        address.setStreet("test");
        address.setCity("test");
        address.setCountry("test");
        person.setAddress(address);

        personRepository.save(person);
        List<Person> byName = personRepository.findByStreet(address.getStreet());
        assertTrue(byName != null && byName.size() > 0);
    }

    @Test
    public void test04_findByNameAndStreet() {
        Person person = new Person();
        person.setName("name");
        person.setLastName("last name");
        person.setAge(5);

        Address address = new Address();
        address.setStreet("test");
        address.setCity("test");
        address.setCountry("test");
        person.setAddress(address);

        personRepository.save(person);
        List<Person> byName = personRepository.findByNameAndStreet(person.getName(), address.getStreet());
        assertTrue(byName != null && byName.size() > 0);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        mongod.stop();
        mongodExe.stop();
    }
}

