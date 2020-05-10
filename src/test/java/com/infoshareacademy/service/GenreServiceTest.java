package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GenreServiceTest {

    @Test
    void addTwoEpochsToListOfEpochs() {

        //GIVEN
        GenreService genreService = new GenreService();
        Book book = new Book();
        List<Genre> genres = new ArrayList<>();
        Genre genre1 = new Genre();
        Genre genre2 = new Genre();
        genre1.setName("Gatunek pierwszy");
        genre2.setName("Gatunek drugi");
        genres.add(genre1);
        genres.add(genre2);
        book.setGenres(genres);
        String result;

        //WHEN
        result = genreService.getGenres(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Gatunek pierwszy, Gatunek drugi")
                .isNotEqualTo("Gatunek pierwszy")
                .isNotEmpty();
    }

    @Test
    void checkIfEmptyWithNoEpochsInEpochsList() {
        //GIVEN
        GenreService genreService = new GenreService();
        Book book = new Book();
        List<Genre> genres = new ArrayList<>();
        book.setGenres(genres);
        String result;

        //WHEN
        result = genreService.getGenres(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEmpty();
    }

}
