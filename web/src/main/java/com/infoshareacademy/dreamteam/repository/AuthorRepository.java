package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface AuthorRepository {
    void save(Author author);

    Author update(Author author);

    Optional<Author> findByName(String name);
}
