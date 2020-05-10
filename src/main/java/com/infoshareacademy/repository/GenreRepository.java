package com.infoshareacademy.repository;

import com.infoshareacademy.Reader;
import com.infoshareacademy.object.Genre;

import java.util.List;

public class GenreRepository {
    private static GenreRepository genreRepository = null;
    private List<Genre> genreList;

    private GenreRepository() {
        Reader reader = new Reader();
        genreList = reader.readGenreList();
    }

    public static GenreRepository getInstance() {
        if (genreRepository == null) {
            genreRepository = new GenreRepository();
        }
        return genreRepository;
    }

    public List<Genre> getGenres() {
        return genreList;
    }


}


