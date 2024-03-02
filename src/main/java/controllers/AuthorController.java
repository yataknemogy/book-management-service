package com.example.demo.controllers;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.service.AuthorService;
import javassist.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;
    private static Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Set<Author> getAllAuthors(Long id){
        logger.info("Request to contact all authors");
        return authorService.getAllAuthor();
    }
    @PostMapping("/add")
    public Author addAuthor(@RequestBody Author author){
        logger.info("Request to add author");
        return authorService.addAuthor(author);
    }
    @GetMapping("/{id}")
    public Optional<Author> getAuthorById(@PathVariable Long id){
        logger.info("Request to get author by id");
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            return author;
        }
        else {
            logger.error("Error: Author with id " + id + " not found ");
        }
        return null;
    }
    @DeleteMapping("/{id}/delete")
    public Author deleteAuthorById(@PathVariable Long id) throws BadRequestException {
        try {
            authorService.deleteById(id);
        }
        catch (Exception e){
            logger.error("Error remove author with id " + id);
        }
        return null;
    }
    @PutMapping("/{id}/update")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author updated) {
        return authorService.updateAuthor(id, updated);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleNotFoundException(NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleBadRequestException(BadRequestException e) {
        return e.getMessage();
    }
}
