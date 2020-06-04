package com.infoshareacademy.dreamteam.service;

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
        return genreRepository.findByName(name).orElse(null);

    }
}
