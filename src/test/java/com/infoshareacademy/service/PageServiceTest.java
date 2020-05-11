package com.infoshareacademy.service;

import com.infoshareacademy.object.Genre;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageServiceTest {

    @Test
    void checkIfContainsNullOrEmpty() {
        //GIVEN
        GenreService genreService = new GenreService();
        List<Genre> result;

        //WHEN
        result = genreService.findAllGenres();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isNotEmpty()
                .doesNotContainNull();

    }

}