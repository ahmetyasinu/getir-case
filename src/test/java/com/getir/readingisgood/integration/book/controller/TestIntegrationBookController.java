package com.getir.readingisgood.integration.book.controller;

import com.getir.readingisgood.book.controller.BookController;
import com.getir.readingisgood.book.controller.dto.AddBookDTO;
import com.getir.readingisgood.book.controller.dto.UpdateBookDTO;
import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.general.TestEntityBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIntegrationBookController {

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;

    private AddBookDTO addBookDTO;
    private UpdateBookDTO updateBookDTO;
    private Book book;
    private String bookId;

    @Before
    public void before() {
        addBookDTO = TestEntityBuilder.getAddBookDTO();

        ResponseEntity<Book> bookResponseEntity = bookController.add(addBookDTO);
        bookId = bookResponseEntity.getBody().getId();
    }

    @Test
    public void testAdd() {
        Optional<List<Book>> optionalBookList = bookRepository.findByName(addBookDTO.getName());
        List<Book> bookList = optionalBookList.get();
        book = bookList.iterator().next();
        Assert.assertEquals(1, bookList.size());
    }

    @Test
    public void testUpdateStock() {
        updateBookDTO = TestEntityBuilder.getUpdateBookDTO(bookId);

        bookController.update(updateBookDTO);

        Optional<List<Book>> optionalBookList = bookRepository.findByName(addBookDTO.getName());
        List<Book> bookList = optionalBookList.get();
        book = bookList.iterator().next();
        Assert.assertEquals(updateBookDTO.getStockCount(), book.getCount());
    }

}
