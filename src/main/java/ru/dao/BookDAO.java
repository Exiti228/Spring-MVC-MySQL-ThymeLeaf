package ru.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.models.Book;


import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book",  new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?",new BeanPropertyRowMapper<>(Book.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET year=?, name=?, author=? WHERE id=?", updatedBook.getYear(),
                updatedBook.getName(), updatedBook.getAuthor(), id);
    }
    public void updateInd(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", updatedBook.getPerson_id(), id);
    }
    public void makeFree(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?",  id);
    }
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?",  new BeanPropertyRowMapper<>(Book.class), id);
    }
}
