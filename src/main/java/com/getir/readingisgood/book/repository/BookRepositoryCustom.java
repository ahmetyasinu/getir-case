package com.getir.readingisgood.book.repository;

import com.getir.readingisgood.book.entity.*;

public interface BookRepositoryCustom {

    Book updateStock(String bookId, Integer stock);
}
