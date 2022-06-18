package ru.models;

public class Book {
    private String name;
    private String author;
    private Integer year;
    private Integer id;
    private Integer person_id;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Book(String name, String author, Integer year, Integer id, Integer person_id) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.id = id;
        this.person_id = person_id;
    }
    public Book() {

    }
}
