package com.library.library.service.impl;

import com.library.library.dto.BookDto;
import com.library.library.dto.BookNameDto;
import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;
import com.library.library.models.Book;
import com.library.library.repository.BookRepository;
import com.library.library.service.BookService;
import com.library.library.service.converter.BookConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(String.valueOf(BookServiceImpl.class));


    private BookRepository bookRepository;
    private BookConverter bookConverter;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookConverter bookConverter){
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    @Override
    public List<BookDto> getBooksByName(String param) {
        logger.info("Get books by param: " + param);
        return StreamSupport.stream(bookRepository
                .findAll().spliterator(), false)
                .map(book -> bookConverter.convertToAllBookInfoDto(book))
                .filter(book -> book.getBookName().contains(param)
                        || book.getBookAuthor().contains(param)
                        || book.getBookGenre().contains(param)
                        || book.getBookPublisher().contains(param))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(BookDto dto) throws ConvertingException {
        logger.info("Add book id = :" + dto.getId());
        Book book = bookConverter.convertToBook(dto);
        logger.info("Add book8 id = :" + book.getId());
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void delete(BookDto dto) throws ConvertingException {
        logger.info("Delete book id = :" + dto.getId());
        Book book = bookConverter.convertToBook(dto);
        bookRepository.delete(book);
    }

    @Transactional
    @Override
    public void edit(BookDto dto) throws ConvertingException {
        logger.info("Edit book id:" + dto.getId());
        Book book = bookConverter.convertToBook(dto);
        bookRepository.save(book);
    }

    @Override
    public BookDto getById(Long id) throws NoSuchEntityException {
        logger.info("Get book by id: " + id);
        Book book =  bookRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return bookConverter.convertToAllBookInfoDto(book);
    }
}
