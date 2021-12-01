package com.library.library.dto;

public class BookDto {
    Long id;
    String bookName;
    String bookAuthor;
    Long bookYear;
    String bookPublisher;
    String bookGenre;
    Long bookCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String name) {
        this.bookName = name;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String author) {
        this.bookAuthor = author;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String genre) {
        this.bookGenre = genre;
    }

    public Long getBookYear() {
        return bookYear;
    }

    public void setBookYear(Long year) {
        this.bookYear = year;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long count) {
        this.bookCount = count;
    }
}
