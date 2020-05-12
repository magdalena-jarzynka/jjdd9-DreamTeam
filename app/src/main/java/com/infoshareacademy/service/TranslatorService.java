package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class TranslatorService {

    public String getTranslators(Book book) {
        return Optional.ofNullable(book)
                .stream()
                .map(Book::getTranslators)
                .flatMap(Collection::stream)
                .map(Author::getName)
                .collect(Collectors.joining(", "));
    }
}
