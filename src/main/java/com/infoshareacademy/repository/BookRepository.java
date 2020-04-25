package com.infoshareacademy.repository;

import com.infoshareacademy.Reader;
import com.infoshareacademy.object.Book;

import java.util.List;

public class BookRepository {
    private static BookRepository bookRepository = null;
    private List<Book> books;

    private BookRepository() {
        Reader reader = new Reader();
        books = reader.readBookList();
    }

    public static BookRepository getInstance() {
        if (bookRepository == null) {
            bookRepository = new BookRepository();
        }
        return bookRepository;
    }

    public List<Book> getBooks() {
        return books;
    }
}