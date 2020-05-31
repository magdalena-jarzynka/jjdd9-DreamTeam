package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Genre;

import javax.ejb.Local;

@Local
public interface GenreRepository {
    void save(Genre genre);

    Genre update(Genre genre);
}
