package ru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.models.Book;
import ru.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOwnerEquals(Person person);
    List<Book> findByNameIsContaining(String str);
}
