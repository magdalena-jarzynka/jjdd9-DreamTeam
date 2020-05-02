package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;

import java.util.List;

public class GenreService {

    public String getGenres(Book book) {
        List<Genre> genres = book.getGenres();
        return String.join(", ", genres.get(0).getName());
    }
}
