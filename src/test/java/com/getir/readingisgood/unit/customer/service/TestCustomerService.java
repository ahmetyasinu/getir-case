package com.getir.readingisgood.unit.customer.service;

import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.exception.ResourceNotFoundException;
import com.getir.readingisgood.general.TestEntityBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class TestCustomerService {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void before() {
        customer = TestEntityBuilder.getCustomer();
    }

    @Test
    public void testAddExistingCustomer() {
        Optional<Customer> optionalCustomer = Optional.of(customer);
        boolean exceptionIsThrown = false;

        Mockito.when(customerRepository.findByEmail(customer.getEmail())).thenReturn(optionalCustomer);

        try {
            Assert.assertNull(customerService.add(customer));
        } catch (IllegalArgumentException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(customerRepository).findByEmail(customer.getEmail());
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testAddCustomer() {
        boolean exceptionIsThrown = false;

        Mockito.when(customerRepository.findByEmail(customer.getEmail())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        try {
            Assert.assertEquals(customer, customerService.add(customer));
        } catch (IllegalArgumentException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(customerRepository).findByEmail(customer.getEmail());
        Mockito.verify(customerRepository).save(customer);
        Assert.assertFalse(exceptionIsThrown);
    }
    
    @Test
    public void testGetByIdCustomerNotFound() {
        boolean exceptionIsThrown = false;

        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

        try {
            Assert.assertNull(customerService.getById(customer.getId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(customerRepository).findById(customer.getId());
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testGetByIdCustomerPresent() {
        boolean exceptionIsThrown = false;

        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        try {
            Assert.assertEquals(customer, customerService.getById(customer.getId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(customerRepository).findById(customer.getId());
        Assert.assertFalse(exceptionIsThrown);
    }

}
