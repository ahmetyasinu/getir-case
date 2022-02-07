package com.getir.readingisgood.order.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.exception.ResourceNotFoundException;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    private OrderRepository orderRepository;
    private BookService bookService;
    private CustomerService customerService;

    @Transactional(readOnly = true)
    public Page<Order> getAllOrdersPageableByCustomerId(String customerId, Pageable pageable) {
        Page<Order> orderPage = orderRepository.findByCustomerId(customerId, pageable);

        if(orderPage == null || orderPage.isEmpty()) {
            throw new ResourceNotFoundException("Order not found. Customer id: " + customerId);
        }
        return orderPage;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrdersByCustomerId(String customerId) {
        Optional<List<Order>> orderListOpt = orderRepository.findByCustomerId(customerId);
        List<Order> orderList = orderListOpt.get();
        if(CollectionUtils.isEmpty(orderList)) {
            throw new ResourceNotFoundException("Order not found. Customer id: " + customerId);
        }
        return orderList;
    }

    @Transactional
    public Order add(Order order) {
        getCustomerByIdAndCheckIfExist(order);
        Double totalOrderPrice = 0.0;
        List<Book> bookList = new ArrayList<>();

        for (Book book : order.getBookList()) {
            Book vtBook = bookService.getById(book.getId());
            totalOrderPrice += vtBook.getPrice() * book.getCount();
            setBookFieldsAndUpdateStock(bookList, book, vtBook);
        }

        order.setBookList(bookList);
        order.setPrice(totalOrderPrice);
        log.info("New order is inserted. Customer id: " + order.getCustomerId());
        return orderRepository.save(order);
    }

    public Customer getCustomerByIdAndCheckIfExist(Order order) {
        return customerService.getById(order.getCustomerId());
    }

    public void setBookFieldsAndUpdateStock(List<Book> bookList, Book book, Book vtBook) {
        book.setName(vtBook.getName());
        book.setPrice(vtBook.getPrice());
        bookList.add(book);
        bookService.updateStockIfAvailable(book.getId(), vtBook.getCount() - book.getCount());
    }

    @Transactional(readOnly = true)
    public Order getById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException("Order not found. Order id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Order> getByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
        Optional<List<Order>> orderListOpt = orderRepository.findByCreatedDateBetween(startDate, endDate);
        List<Order> orderList = orderListOpt.get();
        if(CollectionUtils.isEmpty(orderList)) {
            throw new ResourceNotFoundException("Order not found.");
        }
        return orderList;
    }

}
