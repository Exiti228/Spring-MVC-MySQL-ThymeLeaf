package ru.models;

import javax.persistence.*;
import java.security.Identity;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Column(name = "fio")
    private String fio;
    @Column(name = "year")
    private Integer year;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @OneToMany(mappedBy = "owner")
    List<Book> books;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person(String fio, Integer year) {
        this.fio = fio;
        this.year = year;

    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
    public Person(){

    }
}
