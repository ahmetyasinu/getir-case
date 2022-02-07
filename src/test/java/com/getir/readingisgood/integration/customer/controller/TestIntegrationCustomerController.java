package com.getir.readingisgood.integration.customer.controller;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.customer.controller.CustomerController;
import com.getir.readingisgood.customer.controller.dto.AddCustomerDTO;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.general.TestEntityBuilder;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIntegrationCustomerController {

    @Autowired
    private CustomerController custumerController;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    private AddCustomerDTO addCustomerDTO;
    private String customerId;

    @Before
    public void before() {
        addCustomerDTO = TestEntityBuilder.getAddCustomerDTO();

        ResponseEntity<Customer> customerResponseEntity = custumerController.add(addCustomerDTO);
        customerId = customerResponseEntity.getBody().getId();
    }

    @Test
    public void testAdd() {
        Assert.assertNotNull(customerId);
    }

    @Test
    public void testUpdateStock() {
        Book book = bookRepository.save(TestEntityBuilder.getBook());

        Order order = TestEntityBuilder.getOrder();
        order.setCustomerId(customerId);
        order.setBookList(Arrays.asList(book));
        orderRepository.save(order);

        ResponseEntity<Page<Order>> allOrdersByCustomerId = custumerController.getAllOrdersByCustomerId(customerId);
        String vtCustomerId = allOrdersByCustomerId.getBody().get().map(Order::getCustomerId).findFirst().get();
        Assert.assertEquals(customerId, vtCustomerId);
    }

}
