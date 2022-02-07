package com.getir.readingisgood.unit.book.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.exception.ResourceNotFoundException;
import com.getir.readingisgood.general.TestEntityBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class TestBookService {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;

    private static final Integer BOOK_COUNT = 10;

    @Before
    public void before() {
        book = TestEntityBuilder.getBook();
    }

    @Test
    public void testAddExistingBook() {
        Optional<List<Book>> optionalBookList = Optional.of(Arrays.asList(book));
        boolean exceptionIsThrown = false;

        Mockito.when(bookRepository.findByName(book.getName())).thenReturn(optionalBookList);

        try {
            Assert.assertNull(bookService.add(book));
        } catch (IllegalArgumentException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(bookRepository).findByName(book.getName());
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testAddBook() {
        boolean exceptionIsThrown = false;

        Mockito.when(bookRepository.findByName(book.getName())).thenReturn(Optional.of(Arrays.asList()));
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        try {
            Assert.assertEquals(book, bookService.add(book));
        } catch (IllegalArgumentException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(bookRepository).findByName(book.getName());
        Mockito.verify(bookRepository).save(book);
        Assert.assertFalse(exceptionIsThrown);
    }

    @Test
    public void testUpdateStockBookNotPresent() {
        boolean exceptionIsThrown = false;

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

        try {
            Assert.assertNull(bookService.updateStock(book.getId(), book.getCount()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(bookRepository).findById(book.getId());
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testUpdateStock() {
        boolean exceptionIsThrown = false;
        Book vtBook = new Book();

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(vtBook));
        Mockito.when(bookRepository.updateStock(book.getId(), BOOK_COUNT)).thenReturn(book);

        try {
            Assert.assertEquals(book, bookService.updateStock(book.getId(), BOOK_COUNT));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(bookRepository).findById(vtBook.getId());
        Mockito.verify(bookRepository).updateStock(book.getId(), BOOK_COUNT);
        Assert.assertFalse(exceptionIsThrown);
        Assert.assertEquals(BOOK_COUNT, book.getCount());
    }

    @Test
    public void testUpdateStockNotEnough() {
        boolean exceptionIsThrown = false;

        try {
            bookService.updateStockIfAvailable(book.getId(), 0);
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testUpdateStockEnough() {
        boolean exceptionIsThrown = false;
        Book vtBook = new Book();

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(vtBook));
        Mockito.when(bookRepository.updateStock(book.getId(), BOOK_COUNT)).thenReturn(book);

        try {
            bookService.updateStockIfAvailable(book.getId(), BOOK_COUNT);
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(bookRepository).findById(vtBook.getId());
        Mockito.verify(bookRepository).updateStock(book.getId(), BOOK_COUNT);
        Assert.assertFalse(exceptionIsThrown);
        Assert.assertEquals(BOOK_COUNT, book.getCount());
    }

    @Test
    public void testGetByIdBookNotFound() {
        boolean exceptionIsThrown = false;

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

        try {
            Assert.assertNull(bookService.getById(book.getId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(bookRepository).findById(book.getId());
        Assert.assertTrue(exceptionIsThrown);
    }

    @Test
    public void testGetByIdBookPresent() {
        boolean exceptionIsThrown = false;

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        try {
            Assert.assertEquals(book, bookService.getById(book.getId()));
        } catch (ResourceNotFoundException e) {
            exceptionIsThrown = true;
        }

        Mockito.verify(bookRepository).findById(book.getId());
        Assert.assertFalse(exceptionIsThrown);
    }

}
