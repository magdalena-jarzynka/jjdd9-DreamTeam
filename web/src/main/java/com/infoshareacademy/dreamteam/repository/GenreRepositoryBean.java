package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Genre;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class GenreRepositoryBean implements GenreRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return entityManager.merge(genre);
    }

    @Override
    public Optional<Genre> findByName(String name) {
        Query query = entityManager.createNamedQuery("Genre.findGenreByName");
        query.setParameter("name", name);
        return query.getResultList().stream().findAny();
    }
}
