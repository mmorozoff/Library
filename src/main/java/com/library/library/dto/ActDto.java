package com.library.library.dto;

import com.library.library.models.Book;
import com.library.library.models.Reader;

import java.util.Date;

public class ActDto {

    private Long id;
    private String bookName;
    private Long bookId;
    private String readerName;
    private String startDate;
    private String finishDate;

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() { return bookId; }

    public void setBookId(Long id) { this.bookId = id; }

    public String getBookName()
    {
        return this.bookName;
    }

    public void setBookName(String book) { this.bookName = book; }

    public String getReaderName() { return this.readerName; }

    public void setReaderName(String reader) { this.readerName = reader; }

    public String getStartDate() { return this.startDate; }

    public void setStartDate(String date) { this.startDate = date; }

    public String getFinishDate() { return this.finishDate; }

    public void setFinishDate(String date) { this.finishDate = date; }
}
