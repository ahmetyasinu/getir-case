package com.getir.readingisgood.unit.statistics.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.general.TestEntityBuilder;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.OrderService;
import com.getir.readingisgood.statistics.controller.pojo.StatisticDetail;
import com.getir.readingisgood.statistics.service.StatisticsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class TestStatisticsService {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private OrderService orderService;

    private Order order;

    private static final Integer BOOK_COUNT = 5;
    private static final Double PRICE = 10.0;

    @Before
    public void before() {
        order = TestEntityBuilder.getOrder();
        order.setCreatedDate(LocalDateTime.now());
        Book book = order.getBookList().iterator().next();
        book.setCount(BOOK_COUNT);
        book.setPrice(PRICE);
    }

    @Test
    public void testAddCustomerNull() {
        Mockito.when(orderService.getAllOrdersByCustomerId(order.getCustomerId())).thenReturn(Arrays.asList(order, order));

        List<StatisticDetail> monthlyStatistics = statisticsService.getMonthlyStatistics(order.getCustomerId());

        Mockito.verify(orderService).getAllOrdersByCustomerId(order.getCustomerId());

        StatisticDetail statisticDetail = monthlyStatistics.iterator().next();
        Assert.assertEquals(LocalDateTime.now().getMonth().toString(), statisticDetail.getMonth());
        Assert.assertEquals(Integer.valueOf(10), statisticDetail.getTotalBookCount());
        Assert.assertEquals(Integer.valueOf(2), statisticDetail.getTotalOrderCount());
        Assert.assertEquals(Double.valueOf(40.0), statisticDetail.getTotalPurchasedAmount());
    }
}
