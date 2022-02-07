package com.getir.readingisgood.customer.repository;

import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByEmail(String customerId);
}
