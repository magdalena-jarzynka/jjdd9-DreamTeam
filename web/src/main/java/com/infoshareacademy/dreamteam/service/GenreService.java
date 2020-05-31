package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Genre;
import com.infoshareacademy.dreamteam.repository.GenreRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GenreService {
    @EJB
    private GenreRepository genreRepository;

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.update(genre);
    }

    public Genre findByName(String name) {
        Genre genre = genreRepository.findByName(name).orElse(null);
        return genre;
    }
}
