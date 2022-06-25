package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.models.Book;
import ru.models.Person;
import ru.repositories.BooksRepository;
import ru.repositories.PeopleRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }
    public void save(Person person) {
        peopleRepository.save(person);
    }
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
    public List<Book> getBooksByPersonId(int id) {
        return  booksRepository.findAllByOwnerEquals(peopleRepository.findById(id).orElse(null));

    }
    public void getBooksIsPenalty(List<Book> books) {
        for (Book book : books) {
            Date dateGet = book.getGetAt();
            Date dateNow = new Date();

            if (dateGet != null && TimeUnit.DAYS.convert(dateNow.getTime() - dateGet.getTime(), TimeUnit.MILLISECONDS) >= 10) {
                book.setBookOverdue(true);
            }
            else
                book.setBookOverdue(false);
        }

    }
}
