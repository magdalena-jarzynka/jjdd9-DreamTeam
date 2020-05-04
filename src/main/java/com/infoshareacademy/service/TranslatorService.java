package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class TranslatorService {
    Properties properties = ConstantService.readProperties("constants.properties");

    public String getTranslators(Book book) {
        List<Author> translators = book.getTranslators();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> translators.size() > 0)
                .map(i -> translators.get(0))
                .map(Author::getName)
                .orElse(properties.getProperty("NONE"));
    }
}
