package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.models.Book;
import ru.models.Person;
import ru.services.BooksService;
import ru.services.PeopleService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;

        this.peopleService = peopleService;
    }



    @GetMapping("")
    public String index(Model model, @RequestParam(name = "page", required = false, defaultValue = "-1") int page,
                        @RequestParam(name = "books_per_page", required = false, defaultValue = "-1") int booksPerPage,
                        @RequestParam(name="sort_by_year", required = false, defaultValue = "false") boolean isSorted) {
        if (page == -1 || booksPerPage == -1)
            model.addAttribute("book", booksService.findAllSort(isSorted));
        else
            model.addAttribute("book", booksService.findAllPaginationSort(page, booksPerPage, isSorted));
        return "books/index";
    }
    @PostMapping("")
    public String create(@ModelAttribute("book") Book book){
        booksService.save(book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/ind")
    public String updateInd(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        booksService.updateSetConnection(id, book.getForeignId());
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        //System.out.println(book.getAuthor() + " " + book.getPerson_id());
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
       // System.out.println(booksService.findOne(id).getOwner().getId());
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }
    @PatchMapping("/{id}/free")
    public String makeFree(@PathVariable("id") int id) {
        Book book = booksService.findOne(id);
        book.setOwner(null);
        booksService.update(id, book);

        return "redirect:/books";
    }
    @GetMapping("/search")
    public String search(@RequestParam(name = "searchStr", required = false) String searchStr, Model model) {
        if (searchStr != null) {
            List<Book> books = booksService.findByNameIsContaining(searchStr);
            System.out.println("in");
            List<Person> people = new ArrayList<>();
            for (Book book : books)
                people.add(book.getOwner());
            model.addAttribute("people", people);
            model.addAttribute("books", books);

        }
        else {
            model.addAttribute("people", null);
            model.addAttribute("books", null);
            model.addAttribute("searchStr", "");
        }

        return "books/search";
    }
}
