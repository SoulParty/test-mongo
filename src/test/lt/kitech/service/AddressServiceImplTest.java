package lt.kitech.service;

import lt.kitech.model.Address;

import lt.kitech.service.impl.AddressServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Danielius Kibartas on 2015-09-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AddressServiceImplTest.AddressServiceImplTestContextConfiguration.class)
public class AddressServiceImplTest {

    @Configuration
    static class AddressServiceImplTestContextConfiguration {
        @Bean
        public AddressService addressService() {
            return new AddressServiceImpl();
        }
    }

    @Autowired
    private AddressService addressService;

    @Test
    public void test01_service() {
        assertNotNull(addressService);
    }

    @Test
    public void test02_createAddress() {
        Address address = addressService.create("test", "test", "test");
        assertNotNull(address);
    }
}
