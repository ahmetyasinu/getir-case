package com.getir.readingisgood.book.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookDTO {

    @NotNull(message = "id cannot be empty")
    private String id;

    @NotNull
    @Min(0)
    private Integer stockCount;
}
