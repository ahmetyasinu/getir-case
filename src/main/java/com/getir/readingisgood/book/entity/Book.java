package com.getir.readingisgood.book.entity;

import com.getir.readingisgood.general.entity.AbstractAuditedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Book extends AbstractAuditedEntity {

    private String name;
    private Integer count;
    private Double price;

    public Book(String name, Integer count, Double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public Book(String id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
