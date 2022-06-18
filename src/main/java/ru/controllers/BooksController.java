package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dao.BookDAO;
import ru.dao.PersonDAO;
import ru.models.Book;


@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("book", bookDAO.index());
        System.out.println(bookDAO.index());
        return "books/index";
    }
    @PostMapping("")
    public String create(@ModelAttribute("book") Book book){
        bookDAO.save(book);
        System.out.println("pt");
        return "redirect:/books";
    }
    @PatchMapping("/{id}/ind")
    public String updateInd(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        //System.out.println(book.getAuthor() + " " + book.getPerson_id());
        bookDAO.updateInd(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        //System.out.println(book.getAuthor() + " " + book.getPerson_id());
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        System.out.println(bookDAO.show(id) == null);
        System.out.println(bookDAO.show(id).getName());
        System.out.println(bookDAO.show(id).getPerson_id());
        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        System.out.println(bookDAO.show(id).getAuthor());
        return "books/edit";
    }
    @PatchMapping("/{id}/free")
    public String makeFree(@PathVariable("id") int id) {
        bookDAO.makeFree(id);
        return "redirect:/books";
    }
}
