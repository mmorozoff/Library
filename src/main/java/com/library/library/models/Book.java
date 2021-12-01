package com.library.library.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String bookName;

    @Column(name = "author")
    private String bookAuthor;

    @Column(name = "year")
    private Long bookYear;

    @Column(name = "publisher")
    private String bookPublisher;

    @Column(name = "genre")
    private String bookGenre;

    @Column(name = "count")
    private Long bookCount;

    @OneToMany(mappedBy = "book")
    private Set<Act> acts = new HashSet<>();

    public Set<Act> getActs() {
        return acts;
    }

    public void setActs(Set<Act> acts) {
        this.acts = acts;
    }

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
