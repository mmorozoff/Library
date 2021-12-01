package com.library.library.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="acts")
public class Act {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="book_name")
    private String book;

    @Column(name="reader_name")
    private String reader;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="finish_date")
    private Date finishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook()
    {
        return this.book;
    }

    public void setBook(String book) { this.book = book; }

    public String getReader() { return this.reader; }

    public void setReader(String reader) { this.reader = reader; }

    public Date getStartDate() { return this.startDate; }

    public void setStartDate(Date date) { this.startDate = date; }

    public Date getFinishDate() { return this.finishDate; }

    public void setFinishDate(Date date) { this.finishDate = date; }
}
