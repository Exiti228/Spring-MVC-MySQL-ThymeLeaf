package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.models.Book;
import ru.models.Person;

import ru.services.PeopleService;

import java.util.List;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;


    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;

    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        List<Book> lt = peopleService.getBooksByPersonId(id);
        peopleService.getBooksIsPenalty(lt);
        if (lt.size() == 0)
            lt = null;
        model.addAttribute("books", lt);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {

        //System.out.println(person.getFio());
        peopleService.save(person);
        return "redirect:/people";
      //  return null;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {



        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
