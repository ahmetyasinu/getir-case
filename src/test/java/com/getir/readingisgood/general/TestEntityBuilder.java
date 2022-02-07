package com.getir.readingisgood.general;

import com.getir.readingisgood.book.controller.dto.AddBookDTO;
import com.getir.readingisgood.book.controller.dto.UpdateBookDTO;
import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.customer.controller.dto.AddCustomerDTO;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.controller.dto.OrderedBookDTO;
import com.getir.readingisgood.order.entity.Order;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestEntityBuilder {

    private static final Integer BOOK_COUNT = 10;
    private static final Double PRICE = 20.0;

    public static Book getBook() {
        return new Book(getRandomString(), BOOK_COUNT, PRICE);
    }

    public static Customer getCustomer() {
        return new Customer(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString());
    }

    public static Order getOrder() {
        return new Order(PRICE, Arrays.asList(getBook()), getRandomString());
    }

    public static AddBookDTO getAddBookDTO() {
        return new AddBookDTO(getRandomString(), BOOK_COUNT, PRICE);
    }

    public static UpdateBookDTO getUpdateBookDTO(String id) {
        return new UpdateBookDTO(id, BOOK_COUNT);
    }

    public static AddCustomerDTO getAddCustomerDTO() {
        return new AddCustomerDTO(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString());
    }

    private static String getRandomString() {
        StringBuilder stringBuilder = new StringBuilder("A");
        return stringBuilder.append(getRandomInt()).toString();
    }

    private static int getRandomInt() {
        return ThreadLocalRandom.current().nextInt(20000);
    }

    public static AddOrderDTO getAddOrderDTO(String customerId, List<OrderedBookDTO> bookList) {
        return new AddOrderDTO(bookList, customerId);
    }
}
