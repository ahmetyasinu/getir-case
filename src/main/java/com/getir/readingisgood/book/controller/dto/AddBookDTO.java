package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookDTO {

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 100,
            message = "name must be at least of 2 letters and not more then 100 letters")
    private String name;

    @NotNull
    @Min(0)
    private Integer stockCount;

    @NotNull
    @DecimalMin("0.1")
    private Double price;

    public Book getDomainObject() {
        return new Book(name, stockCount, price);
    }
}
