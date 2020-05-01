package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;

public class BookService {

    private static Map<Long, Book> books;

    public Map<Long, Book> findAllBooks() {
        return BookRepository.getInstance().getBooks();
    }

    public void setBooks(Map<Long, Book> books) {
        BookService.books = books;
    }

    public Map<Long, Book> getBooks() {
        return books;
    }

    public int getBooksSize() {
        return books.size();
    }
}
