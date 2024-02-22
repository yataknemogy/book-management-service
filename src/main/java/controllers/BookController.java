package controllers;

import Model.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getAllBooks(Integer id){
        return bookService.findAll();
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @PutMapping("/{id}/update")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteBookById(@PathVariable Integer id){
        bookService.deleteById(id);
    }
}
