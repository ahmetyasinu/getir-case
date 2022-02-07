package com.getir.readingisgood.book.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class BookService {

    private BookRepository bookRepository;

    @Transactional
    public Book add(Book book) {
        checkBookIsPresent(book);
        log.info("New book is inserted. Book name: " + book.getName());
        return bookRepository.save(book);
    }

    private void checkBookIsPresent(Book book) {
        Optional<List<Book>> bookListOpt = bookRepository.findByName(book.getName());
        List<Book> bookList = bookListOpt.get();
        if (!CollectionUtils.isEmpty(bookList)) {
            throw new IllegalArgumentException("Book is already exist with the same name. Book name: " + book.getName());
        }
    }

    @Transactional
    public Book updateStock(String bookId, Integer stock) {
        Book book = getById(bookId);
        Integer oldStock = book.getCount();

        Book vtBook = bookRepository.updateStock(bookId, stock);
        vtBook.setCount(stock);
        log.info("Book stock count is updated. Old stock count: " + oldStock + " New stock count: " + stock);
        return vtBook;
    }

    @Transactional
    public void updateStockIfAvailable(String bookId, Integer stock) {
        if (stock >= 0) {
            updateStock(bookId, stock);
        } else {
            throw new ResourceNotFoundException("Stock is not enough. Book id: " + bookId);
        }
    }

    @Transactional(readOnly = true)
    public Book getById(String id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (!bookOpt.isPresent()) {
            log.info("Book not found.Book id: " + id);
            throw new ResourceNotFoundException("Book not found. Book id: " + id);
        }
        return bookOpt.get();
    }
}
