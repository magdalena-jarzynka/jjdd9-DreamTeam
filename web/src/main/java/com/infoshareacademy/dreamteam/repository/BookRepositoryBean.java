package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.view.stats.AuthorStatsView;
import com.infoshareacademy.dreamteam.domain.view.stats.BookStatsView;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class BookRepositoryBean implements BookRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        Query query = entityManager.createNamedQuery("Book.findBookByTitle");
        query.setParameter("title", title);
        return query.getResultList().stream().findAny();
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        Query query = entityManager.createNamedQuery("Book.findBookById");
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Long countBooks() {
        Query query = entityManager.createNamedQuery("Book.countAll");
        return (long) query.getSingleResult();
    }

    @Override
    public Long countBooksByAudioAndGenreAndStringOfCharacters(Boolean audio, String genre, String stringOfCharacters) {
        Query query = entityManager.createNamedQuery("Book.countByAudioAndGenreAndStringOfCharacters");
        query.setParameter("audio", audio);
        query.setParameter("genre", genre);
        query.setParameter("stringOfCharacters", "%" + stringOfCharacters + "%");
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
    public List<Book> findBooksByAudioAndGenreAndStringOfCharacters(int offset, int limit, Boolean audio, String genre, String stringOfCharacters) {

        Query query = entityManager.createNamedQuery("Book.findByAudioAndGenreAndStringOfCharacters");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setParameter("audio", audio);
        query.setParameter("genre", genre);
        query.setParameter("stringOfCharacters", "%" + stringOfCharacters + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> findBooksByStringOfCharacters(String stringOfCharacters) {

        Query query = entityManager.createNamedQuery("Book.findByStringOfCharacters");
        query.setParameter("stringOfCharacters", "%" + stringOfCharacters + "%");
        return query.getResultList();
    }

    @Override
    public List<String> getGenres() {
        Query query = entityManager.createNamedQuery("Genre.getGenres");
        return query.getResultList();
    }

    @Override
    public List<BookStatsView> findBookReservationCount() {
        Query query = entityManager.createNamedQuery("Book.getReservationsStats");
        return query.getResultList();
    }
}
