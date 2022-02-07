package com.getir.readingisgood.unit.order.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.exception.ResourceNotFoundException;
import com.getir.readingisgood.general.TestEntityBuilder;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class TestOrderService {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookService bookService;

    @Mock
    private CustomerService customerService;

    private final static Double PRICE = 200.0;
    private Order order;
    private LocalDateTime startDate = LocalDateTime.now().minusDays(5);
    private LocalDateTime endDate = LocalDateTime.now();
    private Pageable pageable;
    private Page<Order> page;
    private Customer customer;

    @Before
    public void before() {
        order = TestEntityBuilder.getOrder();
        page = new PageImpl<>(Arrays.asList(order));
        customer = TestEntityBuilder.getCustomer();
    }

    @Test
    public void testGetAllOrdersPageableByCustomerIdCustomerNull() {
        boolean exceptionIsThrown = false;

        Mockito.when(orderRepository.findByCustomerId(order.getCustomerId(), pageable)).thenReturn(null);
        try {
            Assert.assertNull(orderService.getAllOrdersPageableByCustomerId(order.getCustomerId(), pageable));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findByCustomerId(order.getCustomerId(), pageable);
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testGetAllOrdersPageableByCustomerId() {
        boolean exceptionIsThrown = false;

        Mockito.when(orderRepository.findByCustomerId(order.getCustomerId(), pageable)).thenReturn(page);
        try {
            Assert.assertNotNull(orderService.getAllOrdersPageableByCustomerId(order.getCustomerId(), pageable));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findByCustomerId(order.getCustomerId(), pageable);
        Assert.assertFalse(exceptionIsThrown);
    }

    @Test
    public void testGetAllOrdersByCustomerIdCustomerNull() {
        boolean exceptionIsThrown = false;

        Mockito.when(orderRepository.findByCustomerId(order.getCustomerId())).thenReturn(Optional.of(Arrays.asList()));
        try {
            Assert.assertNull(orderService.getAllOrdersByCustomerId(order.getCustomerId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findByCustomerId(order.getCustomerId());
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testGetAllOrdersByCustomerId() {
        boolean exceptionIsThrown = false;
        Optional<List<Order>> orderList = Optional.of(Arrays.asList(order));

        Mockito.when(orderRepository.findByCustomerId(order.getCustomerId())).thenReturn(orderList);
        try {
            Assert.assertEquals(orderList.get(), orderService.getAllOrdersByCustomerId(order.getCustomerId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findByCustomerId(order.getCustomerId());
        Assert.assertFalse(exceptionIsThrown);
    }

    @Test
    public void testAdd() {
        boolean exceptionIsThrown = false;
        Book book = order.getBookList().iterator().next();

        Mockito.when(customerService.getById(order.getCustomerId())).thenReturn(customer);
        Mockito.when(bookService.getById(book.getId())).thenReturn(book);
        Mockito.when(orderRepository.save(order)).thenReturn(order);

        try {
            Assert.assertEquals(order, orderService.add(order));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(customerService).getById(order.getCustomerId());
        Mockito.verify(bookService).getById(book.getId());
        Mockito.verify(bookService).updateStockIfAvailable(book.getId(), 0);
        Mockito.verify(orderRepository).save(order);
        Assert.assertEquals(PRICE, order.getPrice());
        Assert.assertFalse(exceptionIsThrown);
    }


    @Test
    public void testGetByIdOrderNotFound() {
        boolean exceptionIsThrown = false;

        Mockito.when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());

        try {
            Assert.assertNull(orderService.getById(order.getId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findById(order.getId());
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testGetByIdOrderPresent() {
        boolean exceptionIsThrown = false;

        Mockito.when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        try {
            Assert.assertEquals(order, orderService.getById(order.getId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findById(order.getId());
        Assert.assertFalse(exceptionIsThrown);
    }

    @Test
    public void testGetByDateIntervalOrderNotFound() {
        boolean exceptionIsThrown = false;

        Mockito.when(orderRepository.findByCreatedDateBetween(startDate, endDate)).thenReturn(Optional.of(Arrays.asList()));

        try {
            Assert.assertNull(orderService.getByDateInterval(startDate, endDate));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findByCreatedDateBetween(startDate, endDate);
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testGetByDateInterval() {
        boolean exceptionIsThrown = false;

        Mockito.when(orderRepository.findByCreatedDateBetween(startDate, endDate)).thenReturn(Optional.of(Arrays.asList(order)));

        try {
            Assert.assertEquals(Arrays.asList(order), orderService.getByDateInterval(startDate, endDate));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(orderRepository).findByCreatedDateBetween(startDate, endDate);
        Assert.assertFalse(exceptionIsThrown);
    }

}
