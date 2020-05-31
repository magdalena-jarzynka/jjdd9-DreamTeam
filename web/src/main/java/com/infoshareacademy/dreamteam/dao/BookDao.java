package com.infoshareacademy.dreamteam.dao;


import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface BookDao {

    Optional<Book> findBookById(Long id);

    List<Book> findAll();

    int countBooks();

    int countBooks(String audio, String genre);

    List<Book> findBooks(int offset, int limit);

    List<Book> findBooks(int offset, int limit, String audio, String genre);

    List<String> getGenres();
}
