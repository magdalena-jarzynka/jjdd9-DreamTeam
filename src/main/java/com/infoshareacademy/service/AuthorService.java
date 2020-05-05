package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class AuthorService {
    Properties properties = ConstantService.readProperties("constants.properties");

    public String getAuthors(Book book) {
        List<Author> authors = book.getAuthors();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> !authors.isEmpty())
                .map(i -> authors.get(0))
                .map(Author::getName)
                .orElse(properties.getProperty("NONE"));
    }
}
