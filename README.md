<h1>RESTful сервис для управления книгами</h1>

---
***Сущность "Книга" содержит следующие атрибуты:***

- ID (уникальный идентификатор)
- Название
- Автор
- Год издания
---
***Сущность "Автор" содержит следующие атрибуты:***

- ID (уникальный идентификатор)
- Имя
---

***Repository:***

- Интерфейс BookRepository, наследующий JpaRepository, для работы с сущностями книги
- Интерфейс AuthorRepository, наследующий JpaRepository, для работы с сущностями авторами
---


***Service:***
- Cервис BookService, который будет взаимодействовать с репозиторием для выполнения операций CRUD
- Cервис AuthorService, который будет взаимодействовать с репозиторием для выполнения операций CRUD
---
***Controller:***
- Класс BookController с аннотацией @RestController. В Контроллере методы для обработки HTTP-запросов: GET (получение списка книг и получение книги по ID), POST (добавление новой книги), PUT (обновление существующей книги), DELETE (удаление книги по ID)
- Класс AuthorController с аннотацией @RestController. В Контроллере методы для обработки HTTP-запросов: GET (получение списка авторов и получение автора по ID), POST (добавление нового автора), PUT (обновление существующего автора), DELETE (удаление автора по ID)
---
