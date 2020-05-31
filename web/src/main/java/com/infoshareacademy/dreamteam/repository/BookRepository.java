package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface BookRepository {
    void save(Book book);

    Book update(Book book);

    Optional<Book> findByTitle(String name);
}
