package com.pranav.spring_graphql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Book(String id, String name, int pageCount, String authorId) {

    public static List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("book-1", "Effective Java", 416, "author-1"),
            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            new Book("book-3", "Down Under", 436, "author-3")
    ));

    public static Book add(BookInput bookInput) {
        Book book = new Book(bookInput.id(), bookInput.name(), bookInput.pageCount(), null);
        books.add(book);
        return book;
    }

    public static String delete(String id) {
        Optional<Book> book = Optional.ofNullable(getById(id));
        if (book.isPresent()) {
            books.remove(book.get());
            return id;
        }
        return null;
    }

    public static Book getById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static Book update(BookInput bookInput) {
        Optional<Book> byId = Optional.ofNullable(getById(bookInput.id()));

        if (byId.isPresent()) {
            books.remove(byId.get());
            Book book = new Book(bookInput.id(), bookInput.name(), bookInput.pageCount(), null);
            books.add(book);

            return book;
        }

        return null;
    }
}
