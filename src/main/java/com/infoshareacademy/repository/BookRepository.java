package com.infoshareacademy.repository;

import com.infoshareacademy.Reader;
import com.infoshareacademy.object.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {
    private static BookRepository bookRepository = null;
    private Map<Long, Book> books = new HashMap<>();

    private BookRepository() {
        Reader reader = new Reader();
        List<Book> bookList = reader.readBookList();
        for (int i = 0; i < bookList.size(); i++) {
            books.put((long) (i), bookList.get(i));
        }
    }

    public static BookRepository getInstance() {
        if (bookRepository == null) {
            bookRepository = new BookRepository();
        }
        return bookRepository;
    }

    public Map<Long, Book> getBooks() {
        return books;
    }
}