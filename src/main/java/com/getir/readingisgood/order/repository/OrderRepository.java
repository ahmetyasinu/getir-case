package com.getir.readingisgood.order.repository;

import com.getir.readingisgood.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findByCustomerId(String customerId, Pageable pageable);
    Optional<List<Order>> findByCustomerId(String customerId);
    Optional<List<Order>> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
