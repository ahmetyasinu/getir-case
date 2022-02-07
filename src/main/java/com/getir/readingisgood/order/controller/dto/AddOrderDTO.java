package com.getir.readingisgood.order.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderDTO {

    @Valid
    @NotEmpty
    private List<OrderedBookDTO> bookDTOList;

    @NotEmpty(message = "Customer id cannot be empty")
    private String customerId;

    public Order getDomainObject() {
        return new Order(getBookList(), customerId);
    }

    private List<Book> getBookList() {
        return bookDTOList.stream().map(OrderedBookDTO::getDomainObject).collect(Collectors.toList());
    }
}
