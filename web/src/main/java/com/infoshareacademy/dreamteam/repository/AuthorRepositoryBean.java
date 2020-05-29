package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorRepositoryBean implements AuthorRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save (Author author) {entityManager.persist(author);}

    @Override
    public Author update (Author author) {
        return entityManager.merge(author);
    }
}
