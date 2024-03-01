package com.example.demo.controllers;

import com.example.demo.Model.Book;
import javassist.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private static Logger logger = LoggerFactory.getLogger(BookController.class);

//    МЕТОДЫ

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getAllBooks(Integer id){
        return bookService.findAll();
    }

    @PostMapping("/add")
//    @Operation(summary = "add new book", description = "Add new book in the system")
    public Book addBook(@RequestBody Book book){
        logger.info("Request to add book");
        return bookService.addBook(book);
    }
    @PutMapping("/{id}/update")
//    @Operation(summary = "update book", description = "Update book in the system")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        logger.info("Request to update book");
        return bookService.updateBook(id, updatedBook);
    }
    @GetMapping("/{id}")
//    @Operation(summary = "get book", description = "Get book by id in the system")
    public Optional<Book> getBookById(@PathVariable Integer id) {
        logger.info("Request to get book by id");
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return book;
        }
        else {
            logger.error("Book with id " + id + " not found ");
        }
        return book;
    }
    @DeleteMapping("/{id}/delete")
//    @Operation(summary = "delete book", description = "Delete book by id in the system")
    public void deleteBookById(@PathVariable Long id) {
        logger.info("Request to delete book by id") ;
        try {
            bookService.deleteById(id);
        } catch (Exception e){
            logger.error("Error delete book with id " + id);
        }
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getAllBooksPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        Page<Book> bookPage = bookService.getAllBooksPaged(page, size);
        return ResponseEntity.ok(bookPage);
    }
    @PostMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<?> addAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        return bookService.addAuthorToBook(bookId, authorId)
                .map(book -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());
    }

//    Обработки ERROR
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
