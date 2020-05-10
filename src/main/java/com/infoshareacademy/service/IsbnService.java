package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;

import java.util.Optional;

public class IsbnService {

    public String getIsbn(Book book) {
        return Optional.ofNullable(book)
                .map(Book::getIsbnPdf)
                .filter(isbn -> !((String) isbn).isEmpty())
                .orElse("");
    }
}


