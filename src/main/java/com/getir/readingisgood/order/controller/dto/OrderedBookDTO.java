package com.getir.readingisgood.order.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedBookDTO {

    @NotEmpty(message = "book id cannot be empty")
    @Size(min = 1,
            message = "book id must be at least of 1 letters")
    private String bookId;

    @NotNull
    @Min(1)
    private Integer count;

    public Book getDomainObject() {
        return new Book(bookId, count);
    }
}
