package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int longID;
//
//    @Size(max = 255, message = "Title can't exceed 255 characters")
//    @NotBlank(message = "Title is required!")
//    private String title;
//
//    @Size(max = 255, message = "Author can't exceed 255 characters")
//    @ManyToMany
//    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
//    @NotEmpty(message = "At least one author is required!")
//    private Set<Author> authors = new HashSet<>();
//
//    @NotNull(message = "Error is required!")
//    private int publicationYear;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int longID;

    @Size(max = 255, message = "Title can't exceed 255 characters")
    @NotBlank(message = "Title is required!")
    private String title;

    @Size(max = 255, message = "Author can't exceed 255 characters")
    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @NotEmpty(message = "At least one author is required!")
    private Set<Author> authors = new HashSet<>();

    @NotNull(message = "Publication year is required!")
    private int publicationYear;

    public void setLongID(int longID) {
        this.longID = longID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Set<Author> author) {
        this.authors = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getLongID() {
        return longID;
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return (Set<Author>) authors;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}
