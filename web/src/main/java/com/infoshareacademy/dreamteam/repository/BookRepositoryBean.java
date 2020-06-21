package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class BookRepositoryBean implements BookRepository {
    private static final String STRING_OF_CHARACTERS = "stringOfCharacters";
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
        query.setParameter(STRING_OF_CHARACTERS, "%" + stringOfCharacters + "%");
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
        query.setParameter(STRING_OF_CHARACTERS, "%" + stringOfCharacters + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> findBooksByStringOfCharacters(String stringOfCharacters) {

        Query query = entityManager.createNamedQuery("Book.findByStringOfCharacters");
        query.setParameter(STRING_OF_CHARACTERS, "%" + stringOfCharacters + "%");
        return query.getResultList();
    }

    @Override
    public List<String> getGenres() {
        Query query = entityManager.createNamedQuery("Genre.getGenres");
        return query.getResultList();
    }

    @Override
    public void delete(Long bookId) {

        Optional<Book> bookOptional = findBookById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            entityManager.remove(book);
            for (Author author : book.getAuthors()) {
                author.getBooks().remove(book);
            }
            for (Genre genre : book.getGenres()) {
                genre.getBooks().remove(book);
            }
            for (Epoch epoch : book.getEpochs()) {
                epoch.getBooks().remove(book);
            }
            for (Kind kind : book.getKinds()) {
                kind.getBooks().remove(book);
            }
            for (User user : book.getFavourites()) {
                user.getFavourites().remove(book);
            }
        }
    }
}