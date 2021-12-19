package com.library.library.service;

import com.library.library.dto.BookDto;
import com.library.library.dto.BookNameDto;
import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;

import java.util.List;

public interface BookService {

    void add(BookDto dto) throws ConvertingException;

    void delete(BookDto dto) throws ConvertingException;

    void edit(BookDto dto) throws ConvertingException;

    BookDto getById(Long id) throws NoSuchEntityException;

    List<BookDto> getBooksByName(String bookName);
}
