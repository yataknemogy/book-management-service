# RESTful Service for Book Management

This repository contains a RESTful service for managing books and authors. The project is designed to demonstrate how to build a robust backend application using Spring Boot, JPA, and REST principles.

## Entities

### Book

The "Book" entity contains the following attributes:
- ID (unique identifier)
- Title
- Author
- Publication Year

### Author

The "Author" entity contains the following attributes:
- ID (unique identifier)
- Name

## Repository

- `BookRepository` interface, extending `JpaRepository`, for working with book entities
- `AuthorRepository` interface, extending `JpaRepository`, for working with author entities

## Service

- `BookService` to interact with the repository for performing CRUD operations
- `AuthorService` to interact with the repository for performing CRUD operations

## Controller

- `BookController` class annotated with `@RestController`. This controller has methods for handling HTTP requests:
  - GET (retrieve the list of books and get a book by ID)
  - POST (add a new book)
  - PUT (update an existing book)
  - DELETE (delete a book by ID)

- `AuthorController` class annotated with `@RestController`. This controller has methods for handling HTTP requests:
  - GET (retrieve the list of authors and get an author by ID)
  - POST (add a new author)
  - PUT (update an existing author)
  - DELETE (delete an author by ID)

## Features

- **CRUD Operations**: Create, read, update, and delete books and authors.
- **RESTful API**: Exposes endpoints to interact with the book and author data.
- **Spring Boot**: Utilizes Spring Boot framework for easy setup and development.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- PostgreSQL (or another database supported by Spring Data JPA)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yatakknemogy/book-management-service.git
