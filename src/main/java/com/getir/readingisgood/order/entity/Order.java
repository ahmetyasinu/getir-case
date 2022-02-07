package com.getir.readingisgood.order.entity;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.general.entity.AbstractAuditedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection="order")
public class Order extends AbstractAuditedEntity {

    private Double price;
    private List<Book> bookList;
    private String customerId;

    public Order() {
        bookList = new ArrayList<>();
    }

    public Order(List<Book> bookList, String customerId) {
        this.bookList = bookList;
        this.customerId = customerId;
    }

    public String getCreatedMonth() {
        return this.getCreatedDate().getMonth().toString();
    }
}
