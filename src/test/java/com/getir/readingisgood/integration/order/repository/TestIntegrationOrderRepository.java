package com.getir.readingisgood.integration.order.repository;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.general.TestEntityBuilder;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIntegrationOrderRepository {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    private Customer vtCustomer;
    private Book vtBook;
    private Order vtOrder;
    private Order order;

    @Before
    public void before() {
        Customer customer = TestEntityBuilder.getCustomer();
        vtCustomer = customerRepository.save(customer);

        Book book = TestEntityBuilder.getBook();
        vtBook = bookRepository.save(book);

        order = TestEntityBuilder.getOrder();
        order.setBookList(List.of(vtBook));
        order.setCustomerId(vtCustomer.getId());
        vtOrder = orderRepository.save(order);
    }

    @Test
    public void testFindByEmail() {
        Optional<List<Order>> optionalOrderList = orderRepository.findByCustomerId(vtOrder.getCustomerId());
        List<Order> orderList = optionalOrderList.get();
        Order vtOrder = orderList.iterator().next();

        Assert.assertEquals(vtCustomer.getId(), vtOrder.getCustomerId());
        Assert.assertEquals(order.getPrice(), vtOrder.getPrice());
        Assert.assertEquals(vtBook.getName(), vtOrder.getBookList().iterator().next().getName());
    }

    @Test
    public void testFindByCreatedDateBetween() {
        Optional<List<Order>> optionalOrderList = orderRepository.findByCreatedDateBetween(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(10));
        List<Order> orderList = optionalOrderList.get();
        Order vtOrder = orderList.stream().filter(order1 -> order1.getCustomerId().equals(vtCustomer.getId())).findFirst().get();

        Assert.assertEquals(vtCustomer.getId(), vtOrder.getCustomerId());
        Assert.assertEquals(order.getPrice(), vtOrder.getPrice());
        Assert.assertEquals(vtBook.getName(), vtOrder.getBookList().iterator().next().getName());
    }

}
