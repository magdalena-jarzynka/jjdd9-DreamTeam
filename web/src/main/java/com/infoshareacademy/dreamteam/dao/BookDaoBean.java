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

    private static final int BOOKS_PER_PAGE = 20;

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
    public long countBooks(String audio, String genre) {
        Query query = entityManager.createNamedQuery("Book.countWithAudioAndGenre");
        query.setParameter("audio", audio == null ? null : Boolean.valueOf(audio));
        query.setParameter("genre", genre);
        return (long) query.getSingleResult();
    }

    @Override
    public List<Book> findBooks(int offset) {

        Query query = entityManager.createNamedQuery("Book.findAll");
        query.setFirstResult(offset);
        query.setMaxResults(BOOKS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public List<Book> findBooks(int offset, String audio, String genre) {

        Query query = entityManager.createNamedQuery("Book.findWithAudioAndGenre");
        query.setFirstResult(offset);
        query.setMaxResults(BOOKS_PER_PAGE);
        query.setParameter("audio", audio == null || "blank".equals(audio) ? null : Boolean.valueOf(audio));
        query.setParameter("genre", genre == null || "blank".equals(genre) ? null : genre);
        return query.getResultList();
    }

    @Override
    public List<String> getGenres() {
        Query query = entityManager.createNamedQuery("Genre.getGenres");
        return query.getResultList();
    }
}
