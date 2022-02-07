package com.getir.readingisgood.integration.customer.repository;

import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.general.TestEntityBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIntegrationCustomerRepository {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByEmail() {
        Customer customer = TestEntityBuilder.getCustomer();
        customerRepository.save(customer);

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
        Customer vtCustomer = optionalCustomer.get();

        Assert.assertEquals(customer.getAddress(), vtCustomer.getAddress());
        Assert.assertEquals(customer.getFirstName(), vtCustomer.getFirstName());
        Assert.assertEquals(customer.getLastName(), vtCustomer.getLastName());
    }

}
