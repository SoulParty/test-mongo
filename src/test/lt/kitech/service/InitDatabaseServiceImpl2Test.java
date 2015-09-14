package lt.kitech.service;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import lt.kitech.Application;
import lt.kitech.config.MongoConfig;
import lt.kitech.config.MorphiaConfig;
import lt.kitech.model.Address;
import lt.kitech.model.Person;
import lt.kitech.repository.PersonRepository;
import lt.kitech.repository.PersonRepositoryImpl;
import lt.kitech.service.AddressService;
import lt.kitech.service.PersonService;
import lt.kitech.service.impl.InitDatabaseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = {
        MongoConfig.class, MorphiaConfig.class})
public class InitDatabaseServiceImpl2Test {

    @Mock
    private PersonService personService;

    @Mock
    private AddressService addressService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    MongoClient mongo;

    @Mock
    DB db;

    @InjectMocks
    private InitDatabaseServiceImpl initDatabaseService;

    @Before
    public void before() throws UnknownHostException {
        Address address = new Address();
        Person person = new Person();
        when(mongo.getDB(anyString())).thenReturn(db);
        when(db.collectionExists(anyString())).thenReturn(false);
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
