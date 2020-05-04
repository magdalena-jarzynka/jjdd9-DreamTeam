package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;

import java.util.Optional;
import java.util.Properties;

public class IsbnService {
    Properties properties = ConstantService.readProperties("constants.properties");

    public String getIsbn(Book book) {
        return Optional.ofNullable(book)
                .map(Book::getIsbnPdf)
                .filter(fragment -> ((String) fragment).length() > 0)
                .orElse(properties.getProperty("NONE"));
    }
}


