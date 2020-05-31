package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Genre;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface GenreRepository {
    void save(Genre genre);

    Genre update(Genre genre);

    Optional<Genre> findByName(String name);
}
