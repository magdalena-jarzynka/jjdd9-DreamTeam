package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.List;
import java.util.Optional;

public class AuthorService {

    public static final String NONE = " - ";

    public String getAuthors(Book book) {
        List<Author> authors = book.getAuthors();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> authors.size() > 0)
                .map(i -> authors.get(0))
                .map(Author::getName)
                .orElse(NONE);
    }
}
