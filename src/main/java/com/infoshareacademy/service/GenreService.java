package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;

import java.util.List;
import java.util.Optional;

public class GenreService {

    public String getGenres(Book book) {
        List<Genre> genres = book.getGenres();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> genres.size() > 0)
                .map(i -> genres.get(0))
                .map(Genre::getName)
                .orElse(" - ");
    }
}
