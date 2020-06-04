package com.infoshareacademy.dreamteam.dao;

import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class BookDaoBean implements BookDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Book> findBookById(Long id) {
        Query query = entityManager.createNamedQuery("Book.findBookById");
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public long countBooks() {
        Query query = entityManager.createNamedQuery("Book.countAll");
        return (long) query.getSingleResult();
    }

    @Override
    public long countBooksByAudioAndGenre(Boolean audio, String genre) {
        Query query = entityManager.createNamedQuery("Book.countByAudioAndGenre");
        query.setParameter("audio", audio);
        query.setParameter("genre", genre);
        return (long) query.getSingleResult();
    }

    @Override
    public List<Book> findBooks(int offset, int limit) {

        Query query = entityManager.createNamedQuery("Book.findAll");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public List<Book> findBooksByAudioAndGenre(int offset, int limit, Boolean audio, String genre) {

        Query query = entityManager.createNamedQuery("Book.findByAudioAndGenre");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setParameter("audio", audio);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public List<String> getGenres() {
        Query query = entityManager.createNamedQuery("Genre.getGenres");
        return query.getResultList();
    }
}
