package com.pranav.spring_graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @QueryMapping(name = "bookById")
    public Book getBookById(@Argument String id) {
        return Book.getById(id);
    }

    @QueryMapping(name = "allBooks")
    public List<Book> getAllBooks() {
        return Book.books;
    }

    @MutationMapping(name = "addBook")
    public Book addBook(@Argument(name = "book") BookInput bookInput) {
        return Book.add(bookInput);
    }

    @MutationMapping(name = "updateBook")
    public Book deleteBook(@Argument(name = "book") BookInput bookInput) {
        return Book.update(bookInput);
    }

    @MutationMapping(name = "deleteBook")
    public String updateBook(@Argument(name = "id") String id) {
        return Book.delete(id);
    }

    @SchemaMapping(value = "author")
    public Author fetchAuthorForBook(Book book) {
        return Author.getById(book.authorId());
    }
}
