package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;

public class BookService {

    private static Map<Long, Book> books;

    public void setBooks(Map<Long, Book> books) {
        BookService.books = books;
    }

    public int getBooksSize() {
        return books.size();
    }
}
