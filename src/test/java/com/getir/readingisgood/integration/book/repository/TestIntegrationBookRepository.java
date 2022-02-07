package com.getir.readingisgood.integration.book.repository;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.general.TestEntityBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIntegrationBookRepository {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByName() {
        Book book = TestEntityBuilder.getBook();
        bookRepository.save(book);

        Optional<List<Book>> bookListOpt = bookRepository.findByName(book.getName());
        List<Book> bookList = bookListOpt.get();
        Book book1 = bookList.iterator().next();

        Assert.assertEquals(1, bookList.size());
        Assert.assertEquals(book.getName(), book1.getName());
        Assert.assertEquals(book.getCount(), book1.getCount());
    }

}
