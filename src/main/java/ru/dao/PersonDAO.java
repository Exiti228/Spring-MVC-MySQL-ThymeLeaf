package ru.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",  new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",new BeanPropertyRowMapper<>(Person.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(fio, year) VALUES(?, ?)", person.getFio(), person.getYear());
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fio=?, year=? WHERE id=?", updatedPerson.getFio(),
                updatedPerson.getYear(), id);
    }
}