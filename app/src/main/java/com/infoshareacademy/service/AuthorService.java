package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorService {

    public String getAuthors(Book book) {
        return Optional.ofNullable(book)
                .stream()
                .map(Book::getAuthors)
                .flatMap(Collection::stream)
                .map(Author::getName)
                .collect(Collectors.joining(", "));
    }
}
