package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.entity.Genre;
import com.infoshareacademy.dreamteam.domain.view.GenreView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GenreMapper {

    public GenreView mapEntityToView(Genre genre) {
        GenreView genreView = new GenreView();
        genreView.setId(genre.getId());
        genreView.setName(genre.getName());
        return genreView;
    }

}
