package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;
import com.infoshareacademy.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class GenreService {
    Properties properties = ConstantService.readProperties("constants.properties");

    public String getGenres(Book book) {
        List<Genre> genres = book.getGenres();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> !genres.isEmpty())
                .map(i -> genres.get(0))
                .map(Genre::getName)
                .orElse(properties.getProperty("NONE"));
    }

    public List<Genre> findAllGenres() {
        return GenreRepository.getInstance().getGenres();
    }
}
