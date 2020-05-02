package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;

public class BookService {
    public Map<Long, Book> findAllBooks() {
        return BookRepository.getInstance().getBooks();
    }

    public int geBooksSize() {
        return findAllBooks().size();
    }
}
