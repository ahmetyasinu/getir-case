package com.getir.readingisgood.integration.order.controller;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.general.TestEntityBuilder;
import com.getir.readingisgood.order.controller.OrderController;
import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.controller.dto.OrderedBookDTO;
import com.getir.readingisgood.order.entity.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIntegrationOrderController {

    @Autowired
    private OrderController orderController;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private AddOrderDTO addOrderDTO;
    private String orderId;
    private String customerId;

    @Before
    public void before() {
        Customer customer = customerRepository.save(TestEntityBuilder.getCustomer());
        customerId = customer.getId();
        Book book = bookRepository.save(TestEntityBuilder.getBook());
        OrderedBookDTO orderedBookDTO = new OrderedBookDTO(book.getId(), book.getCount());

        addOrderDTO = TestEntityBuilder.getAddOrderDTO(customer.getId(), Arrays.asList(orderedBookDTO));

        ResponseEntity<Order> orderResponseEntity = orderController.add(addOrderDTO);
        orderId = orderResponseEntity.getBody().getId();
    }

    @Test
    public void testAdd() {
        Assert.assertNotNull(orderId);
    }

    @Test
    public void testGetById() {
        Assert.assertNotNull(orderController.getById(orderId));
    }

    @Test
    public void testUpdateStock() {
        ResponseEntity<List<Order>> orderByDateInterval = orderController.getByDateInterval(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        Order order = orderByDateInterval.getBody().stream().filter(order1 -> order1.getCustomerId().equals(customerId)).findFirst().orElse(null);

        Assert.assertNotNull(order);
    }

}
