package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

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

    @Override
    public Optional<Book> findByTitle(String title) {
        Query query = entityManager.createNamedQuery("Book.findBookByTitle");
        query.setParameter("title", title);
        return query.getResultList().stream().findAny();
    }
}
