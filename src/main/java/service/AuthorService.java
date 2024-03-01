package com.example.demo.service;


import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    public Set<Author> getAllAuthor(){
        return (Set<Author>) authorRepository.findAll();
    }
    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }
    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }
    public void deleteById(Long id){
        authorRepository.deleteById(id);
    }
    public Author updateAuthor(Long id, Author updatedAuthor) {
        if(authorRepository.existsById(id)){
            updatedAuthor.setId(id);
            return authorRepository.save(updatedAuthor);
        }
        else{
            throw new IllegalArgumentException("Author " + id + " isn't found!");
        }
    }



    public Page<Author> getAllAuthorPaged(int page, int size){
        PageRequest pageable = PageRequest.of(page, size);
        return authorRepository.findAll(pageable);
    }

}
