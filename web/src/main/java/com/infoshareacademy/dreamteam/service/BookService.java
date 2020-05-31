package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.repository.BookRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BookService {

    @EJB
    private BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }
    public Book update(Book book) {
        return bookRepository.update(book);
    }

}
