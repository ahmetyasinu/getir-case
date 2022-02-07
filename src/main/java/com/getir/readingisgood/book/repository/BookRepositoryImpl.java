package com.getir.readingisgood.book.repository;

import com.getir.readingisgood.book.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Override
    public Book updateStock(String bookId, Integer stock) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(bookId));
        Update update = new Update();
        update.set("count", stock);
        Book book = mongoTemplate.findAndModify(query, update, Book.class);
        return book;
    }
}
