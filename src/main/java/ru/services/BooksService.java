package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.models.Book;
import ru.models.Person;
import ru.repositories.BooksRepository;
import ru.repositories.PeopleRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }
    public List<Book> findAll() {
        return booksRepository.findAll();
    }
    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }
    public void save(Book book) {

        booksRepository.save(book);
    }
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
    public void updateSetConnection(int idBook, int idPerson) {
        Book book = booksRepository.findById(idBook).orElse(null);
        Person person = peopleRepository.findById(idPerson).orElse(null);
        book.setOwner(person);
        book.setGetAt(new Date());
    }
    public List<Book> findAllPaginationSort(int page, int itemsPerPage, boolean isSorted) {
        if (isSorted)
            return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }
    public List<Book> findAllSort(boolean isSorted) {
        if (isSorted)
            return booksRepository.findAll(Sort.by("year"));
        return booksRepository.findAll();
    }
    public List<Book> findByNameIsContaining(String str) {
        return booksRepository.findByNameIsContaining(str);
    }

}
