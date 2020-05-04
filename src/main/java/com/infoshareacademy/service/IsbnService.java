package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;

import java.util.Optional;

public class IsbnService {

    public static final String NONE = " - ";

    public String getIsbn(Book book) {
        return Optional.ofNullable(book)
                .map(Book::getIsbnPdf)
                .filter(fragment -> ((String) fragment).length() > 0)
                .orElse(NONE);
    }
}


