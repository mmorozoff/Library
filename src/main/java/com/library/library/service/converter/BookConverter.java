package com.library.library.service.converter;

import com.library.library.dto.BookDto;
import com.library.library.dto.BookNameDto;
import com.library.library.exception.ConvertingException;
import com.library.library.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {

    public BookNameDto convertToBookNameDto(Book book){
        BookNameDto dto = new BookNameDto();
        dto.setId(book.getId());
        dto.setBookName(book.getBookName());
        return dto;
    }

    public BookDto convertToAllBookInfoDto(Book book){
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setBookName(book.getBookName());
        dto.setBookAuthor(book.getBookAuthor());
        dto.setBookYear(book.getBookYear());
        dto.setBookCount(book.getBookCount());
        dto.setBookPublisher(book.getBookPublisher());
        dto.setBookGenre(book.getBookGenre());
        return dto;
    }

    public Book convertToBook(BookDto book){
        throwExceptionIfDtoIsNotValid(book);
        Book dto = new Book();
        dto.setId(book.getId());
        dto.setBookName(book.getBookName());
        dto.setBookAuthor(book.getBookAuthor());
        dto.setBookYear(book.getBookYear());
        dto.setBookCount(book.getBookCount());
        dto.setBookPublisher(book.getBookPublisher());
        dto.setBookGenre(book.getBookGenre());
        return dto;
    }

    private void throwExceptionIfDtoIsNotValid(BookDto dto) throws ConvertingException {
        if(dto == null){
            throw new ConvertingException("Book information must be not null.");
        }
        if(dto.getBookName() == null || dto.getBookAuthor() == null || dto.getBookGenre() == null
        || dto.getBookPublisher() == null || dto.getBookYear() < 1400 || dto.getBookYear() > 2021
                || dto.getBookCount() < 0){
            throw new ConvertingException("Fields must be not empty.");
        }
    }
}
