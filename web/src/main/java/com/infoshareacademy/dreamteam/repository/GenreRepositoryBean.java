package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Genre;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
