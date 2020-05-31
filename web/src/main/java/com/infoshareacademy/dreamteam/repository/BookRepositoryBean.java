package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BookRepositoryBean implements BookRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save (Book book) {entityManager.persist(book);}

    @Override
    public Book update (Book book) {
        return entityManager.merge(book);
    }
}
