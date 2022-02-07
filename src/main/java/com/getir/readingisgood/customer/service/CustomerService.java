package com.getir.readingisgood.customer.service;

import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Transactional
    public Customer add(Customer customer) {
        Optional<Customer> customerByEmail = customerRepository.findByEmail(customer.getEmail());
        if(customerByEmail.isPresent()) {
            throw new IllegalArgumentException("The operation cannot be performed because there is a registered user in the system with the same email.");
        }

        log.info("New customer is inserted. Customer name: " + customer.getFullName());
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer getById(String id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.orElseThrow(() -> new ResourceNotFoundException("Customer not found. Customer id: " + id));
    }
}
