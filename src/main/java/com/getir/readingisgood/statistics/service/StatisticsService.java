package com.getir.readingisgood.statistics.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.OrderService;
import com.getir.readingisgood.statistics.controller.pojo.StatisticDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StatisticsService {

    private OrderService orderService;

    @Transactional(readOnly = true)
    public List<StatisticDetail> getMonthlyStatistics(String customerId) {
        List<Order> orderList = orderService.getAllOrdersByCustomerId(customerId);
        Map<String, List<Order>> monthOrderListMap = orderList.stream().collect(Collectors.groupingBy(Order::getCreatedMonth));

        List<StatisticDetail> statisticDetailList = getStatisticDetails(monthOrderListMap);
        return statisticDetailList;
    }

    public List<StatisticDetail> getStatisticDetails(Map<String, List<Order>> monthOrderListMap) {
        List<StatisticDetail> statisticDetailList = new ArrayList<>();

        monthOrderListMap.forEach((month, orders) -> {
            Integer totalOrderCount = orders.size();
            Integer totalBookCount = 0;
            Double totalPurchasedAmount = 0.0;
            for (Order order : orders) {
                totalBookCount += order.getBookList().stream().map(Book::getCount).reduce(0, Integer::sum);
                totalPurchasedAmount += order.getPrice();
            }

            StatisticDetail statisticDetail = new StatisticDetail(month, totalOrderCount, totalBookCount, totalPurchasedAmount);
            statisticDetailList.add(statisticDetail);
        });
        return statisticDetailList;
    }
}
