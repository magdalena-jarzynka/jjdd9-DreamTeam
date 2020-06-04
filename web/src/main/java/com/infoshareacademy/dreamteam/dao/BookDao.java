package com.infoshareacademy.dreamteam.dao;


import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface BookDao {

    Optional<Book> findBookById(Long id);

    long countBooks();

    long countBooksByAudioAndGenre(Boolean audio, String genre);

    List<Book> findBooks(int offset, int limit);

    List<Book> findBooksByAudioAndGenre(int offset, int limit, Boolean audio, String genre);

    List<String> getGenres();
}
