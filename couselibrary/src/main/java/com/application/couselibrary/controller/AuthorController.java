package com.application.couselibrary.controller;

import com.application.couselibrary.entity.Author;
import com.application.couselibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    private String listAuthors(Model model){
        model.addAttribute("authors",authorService.findAllAuthors());
        return "authors";
    }

    @GetMapping("/remove-author/{id}")
    public String deleteAuthor(@PathVariable Long id,Model model){
        authorService.deleteAuthor(id);
        model.addAttribute("authors",authorService.findAllAuthors());
        return "authors";
    }

    @GetMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id,Model model){
        model.addAttribute("author",authorService.findAuthorById(id));
        return "update-author";
    }
    @PostMapping("/update-author/{id}")
    public String saveUpdatedAuthor(@PathVariable Long id, Author author, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors())
            return "update-author";
        authorService.updateAuthor(author);
        model.addAttribute("authors",authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/add-author")
    public String showCreateAuthor(Author author){
        return "add-author";
    }
    @PostMapping("/save-author")
    public String saveAuthor(Author author,BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "add-author";
        authorService.createAuthor(author);
        model.addAttribute("authors",authorService.findAllAuthors());
        return "redirect:/authors";
    }
}
