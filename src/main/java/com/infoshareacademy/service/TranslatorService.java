package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.List;

public class TranslatorService {

    private String getTranslators(Book book) {
        List<Author> translators = book.getTranslators();
        return String.join(", ", translators.get(0).getName());
    }
}
