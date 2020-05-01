package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.List;

public class AuthorService {

    private String getAuthors(Book book) {
        List<Author> authors = book.getAuthors();
        return String.join(", ", authors.get(0).getName());
    }
}
