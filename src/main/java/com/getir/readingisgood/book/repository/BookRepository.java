package com.getir.readingisgood.book.repository;

import com.getir.readingisgood.book.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    Optional<List<Book>> findByName(String name);
}
