package lt.kitech.service;

import lt.kitech.model.Address;
import lt.kitech.model.Person;
import lt.kitech.repository.PersonRepository;
import lt.kitech.service.AddressService;
import lt.kitech.service.PersonService;
import lt.kitech.service.impl.InitDatabaseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class InitDatabaseServiceImpl2Test {

    @Mock
    private PersonService personService;

    @Mock
    private AddressService addressService;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private InitDatabaseServiceImpl initDatabaseService;

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
