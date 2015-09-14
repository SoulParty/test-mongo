package lt.kitech.service;

import com.mongodb.MongoClient;
import lt.kitech.config.MongoConfig;
import lt.kitech.config.MorphiaConfig;
import lt.kitech.model.Address;
import lt.kitech.model.Person;
import lt.kitech.repository.PersonRepositoryImpl;
import lt.kitech.service.impl.AddressServiceImpl;
import lt.kitech.service.impl.InitDatabaseServiceImpl;
import lt.kitech.repository.PersonRepository;
import lt.kitech.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = {InitDatabaseServiceImplTest.InitDatabaseImplTestContextConfiguration.class},
        locations = {"classpath:/application.properties"})
public class InitDatabaseServiceImplTest {

    @Configuration
    @Import(value = {MongoConfig.class, MorphiaConfig.class,})
    static class InitDatabaseImplTestContextConfiguration {
        @Bean
        public PersonService personService() {
            return mock(PersonServiceImpl.class);
        }
        @Bean
        public AddressService addressService() {
            return mock(AddressServiceImpl.class);
        }
        @Bean
        public InitDatabaseService initDatabaseService() {
            return new InitDatabaseServiceImpl();
        }
        @Bean
        public PersonRepository personRepository() {
            return mock(PersonRepositoryImpl.class);
        }
        @Bean
        public MongoClient mongo() {
            return mock(MongoClient.class);
        }
    }

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private InitDatabaseService initDatabaseService;

    @Before
    public void before() {
        Address address = new Address();
        Person person = new Person();
        when(addressService.create(anyString(), anyString(), anyString())).thenReturn(address);
        when(personService.create(anyString(), anyString(), anyInt(), any(Address.class))).thenReturn(person);
    }

    @Test
    public void test01_service() {
        assertNotNull(personService);
        assertNotNull(addressService);
        assertNotNull(initDatabaseService);
    }

    @Test
    public void test02_createPeople() {
        int numberOfPeople = 100;
        initDatabaseService.createPeople(numberOfPeople);
        verify(personService, times(numberOfPeople)).create(anyString(), anyString(), anyInt(), any(Address.class));
        verify(addressService, times(numberOfPeople)).create(anyString(), anyString(), anyString());
    }
}
