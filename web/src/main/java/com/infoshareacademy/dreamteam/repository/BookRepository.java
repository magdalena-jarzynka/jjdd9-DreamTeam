package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.view.stats.AuthorStatsView;
import com.infoshareacademy.dreamteam.domain.view.stats.BookStatsView;

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

    Long countBooksByAudioAndGenreAndStringOfCharacters(Boolean audio, String genre, String stringOfCharacters);

    List<Book> findBooks(int offset, int limit);

    List<Book> findBooksByAudioAndGenreAndStringOfCharacters(int offset, int limit, Boolean audio, String genre, String stringOfCharacters);

    List<Book> findBooksByStringOfCharacters(String stringOfCharacters);

    List<String> getGenres();

    List<BookStatsView> findBookReservationCount();
}
