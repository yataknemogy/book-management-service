package com.example.demo.service;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book>findAll(){
        return bookRepository.findAll();
    }
    public Book save(Book book){
        return bookRepository.save(book);
    }
    public Optional<Book> deleteById(Long id){
        bookRepository.deleteById(id);
        return null;
    }
    public Optional<Book>getBookById(Integer id){
        return bookRepository.findById(Long.valueOf(id));
    }
    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById((long) Math.toIntExact(id));
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setTitle(updatedBook.getTitle());
            bookToUpdate.setAuthors(updatedBook.getAuthors());
            bookToUpdate.setPublicationYear(updatedBook.getPublicationYear());
            return bookRepository.save(bookToUpdate);
        }
        else {
            throw new IllegalArgumentException("Book with id " + id + " not found!");
        }
    }
    public Page<Book>getAllBooksPaged(int page, int size){
        PageRequest pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }
    @Transactional
    public Optional<Book> addAuthorToBook(Long bookId, Long authorId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (bookOptional.isPresent() && authorOptional.isPresent()) {
            Book book = bookOptional.get();
            Author author = authorOptional.get();
            book.getAuthors().add(author);
            return Optional.of(bookRepository.save(book));
        }
        return Optional.empty();
    }
}
