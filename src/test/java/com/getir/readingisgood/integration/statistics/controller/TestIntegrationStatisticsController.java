package com.getir.readingisgood.integration.statistics.controller;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.general.TestEntityBuilder;
import com.getir.readingisgood.order.controller.OrderController;
import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.controller.dto.OrderedBookDTO;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.statistics.controller.StatisticsController;
import com.getir.readingisgood.statistics.controller.dto.StatisticDetailsDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIntegrationStatisticsController {

    @Autowired
    private StatisticsController statisticsController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private String customerId;

    @Before
    public void before() {
        Customer customer = customerRepository.save(TestEntityBuilder.getCustomer());
        customerId = customer.getId();
        Book book = bookRepository.save(TestEntityBuilder.getBook());
        OrderedBookDTO orderedBookDTO = new OrderedBookDTO(book.getId(), book.getCount());

        AddOrderDTO addOrderDTO = TestEntityBuilder.getAddOrderDTO(customer.getId(), Arrays.asList(orderedBookDTO));
        orderController.add(addOrderDTO);
    }

    @Test
    public void testGetMonthlyStatistics() {
        ResponseEntity<List<StatisticDetailsDTO>> monthlyStatistics = statisticsController.getMonthlyStatistics(customerId);
        StatisticDetailsDTO statisticDetailsDTO = monthlyStatistics.getBody().get(0);

        Assert.assertNotNull(statisticDetailsDTO);
    }

}
