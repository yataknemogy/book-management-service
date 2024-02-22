package service;

import Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book>findAll(){
        return bookRepository.findAll();
    }
    public Book save(Book book){
        return bookRepository.save(book);
    }
    public void deleteById(Integer id){
        bookRepository.deleteById(id);
    }
    public Optional<Book>getBookById(Integer id){
        return bookRepository.findById(id);
    }
    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(Math.toIntExact(id));
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setTitle(updatedBook.getTitle());
            bookToUpdate.setAuthor(updatedBook.getAuthor());
            bookToUpdate.setPublicationYear(updatedBook.getPublicationYear());
            return bookRepository.save(bookToUpdate);
        }
        else {
            throw new IllegalArgumentException("Book with id " + id + " not found!");
        }
    }
}
