package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface BookRepository {
    void save(Book book);

    Book update(Book book);

    Optional<Book> findByTitle(String name);

    Optional<Book> findBookById(Long id);

    Long countBooks();

    Long countBooksByAudioAndGenre(Boolean audio, String genre);

    List<Book> findBooks(int offset, int limit);

    List<Book> findBooksByAudioAndGenre(int offset, int limit, Boolean audio, String genre);

    List<String> getGenres();
}
