package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PersistBookDTO {

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 100,
            message = "name must be at least of 2 letters and not more then 100 letters")
    private String name;

    @NotNull
    private Integer stockCount;

    @NotNull
    private Double price;

    public Book getDomainObject() {
        return new Book(name, stockCount, price);
    }
}
