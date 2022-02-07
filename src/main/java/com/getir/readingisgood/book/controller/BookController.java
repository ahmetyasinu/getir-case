package com.getir.readingisgood.book.controller;

import com.getir.readingisgood.book.controller.dto.AddBookDTO;
import com.getir.readingisgood.book.controller.dto.UpdateBookDTO;
import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "/add")
    public ResponseEntity<Book> add(@RequestBody @Valid AddBookDTO addBookDTO) {
        Book book = bookService.add(addBookDTO.getDomainObject());
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = "/updateStock")
    public ResponseEntity<Book> update(@RequestBody @Valid UpdateBookDTO updateBookDTO) {
        Book book = bookService.updateStock(updateBookDTO.getId(), updateBookDTO.getStockCount());
        return ResponseEntity.ok(book);
    }

}
