package lt.kitech.service;

import lt.kitech.repository.PersonRepository;
import lt.kitech.repository.PersonRepositoryImpl;
import lt.kitech.model.Person;

import lt.kitech.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
@SpringApplicationConfiguration(classes = PersonServiceImplTest.PersonServiceImplTestContextConfiguration.class)
public class PersonServiceImplTest {

    @Configuration
    static class PersonServiceImplTestContextConfiguration {
        @Bean
        public PersonService personService() {
            return new PersonServiceImpl();
        }
        @Bean
        public PersonRepository personRepository() {
            return mock(PersonRepositoryImpl.class);
        }
    }

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void before() {
        List<Person> personList = new ArrayList<>();
        personList.add(personService.create("test", "test", 1, null));

        when(personRepository.findByName("test")).thenReturn(personList);
        when(personRepository.findByNameAndStreet("test", "test")).thenReturn(personList);
        when(personRepository.findByStreet("test")).thenReturn(personList);
    }

    @Test
    public void test01_service() {
        assertNotNull(personService);
    }

    @Test
    public void test02_createPerson() {
        Person person = personService.create("test", "test", 1, null);
        assertNotNull(person);
        verify(personRepository, atLeastOnce()).save(person);
    }

    @Test
    public void test03_findPeople() {
        //find just created person
        personService.create("test", "test", 1, null);
        List<Person> personList = personService.findPeople("test", "test");
        assertNotNull(personList);
        verify(personRepository, times(1)).findByNameAndStreet("test", "test");
        assertTrue(personList.size() == 1);

        //find created people during initDb
        personList = personService.findPeople(null, "test");
        assertNotNull(personList);
        assertTrue(personList.size() > 0);
        verify(personRepository, times(1)).findByStreet("test");

        //find nothing
        personList = personService.findPeople(null, null);
        assertTrue(personList.size() == 0);
        verify(personRepository, never()).findByNameAndStreet(null, null);
        verify(personRepository, never()).findByStreet(null);
    }

}
