package com.library.library.repository;

import com.library.library.dto.BookDto;
import com.library.library.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<BookDto> getByBookName(String bookName);
}
