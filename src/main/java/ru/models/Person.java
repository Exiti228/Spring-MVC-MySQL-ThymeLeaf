package ru.models;

public class Person {
    private String fio;
    private Integer year;
    private Integer id;


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

    public Person(String fio, Integer year, Integer id) {
        this.fio = fio;
        this.year = year;
        this.id = id;
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
