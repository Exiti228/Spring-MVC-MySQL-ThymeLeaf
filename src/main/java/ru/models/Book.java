package ru.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "year")
    private Integer year;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "get_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date getAt;
    @Transient
    private Integer foreignId;
    @Transient
    private boolean isBookOverdue;

    public boolean isBookOverdue() {
        return isBookOverdue;
    }

    public void setBookOverdue(boolean bookOverdue) {
        isBookOverdue = bookOverdue;
    }

    public Date getGetAt() {
        return getAt;
    }

    public void setGetAt(Date getAt) {
        this.getAt = getAt;
    }

    public Integer getForeignId() {
        return foreignId;
    }

    public void setForeignId(Integer foreignId) {
        this.foreignId = foreignId;
    }

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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Book(String name, String author, Integer year, Person owner) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.owner = owner;
    }
    public Book() {

    }


}
