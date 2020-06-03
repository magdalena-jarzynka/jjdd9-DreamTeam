package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Genre;
import org.hibernate.Hibernate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Optional<Book> findBookByTitle(String title) {
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
    public List<Book> findAll() {
        Query query = entityManager.createNamedQuery("Book.findAll");
        List<Book> books = query.getResultList();
        for (Book book : books) {
            Hibernate.initialize(book.getAuthors());
            Hibernate.initialize(book.getGenres());
            Hibernate.initialize(book.getEpochs());
            Hibernate.initialize(book.getKinds());
            Hibernate.initialize(book.getTranslators());
            Hibernate.initialize(book.getFavourites());
            Hibernate.initialize(book.getReservations());
        }
        return books;
    }

    @Override
    public int countBooks() {
        return findAll().size();
    }

    @Override
    public int countBooks(String audio, String genre) {
        return findBooks(0, Integer.MAX_VALUE, audio, genre).size();
    }

    @Override
    public List<Book> findBooks(int offset, int limit) {

        List<Book> allBooks = findAll();
        int toIndex = Math.min(offset + limit, allBooks.size());
        return allBooks.subList(offset, toIndex);
    }

    @Override
    public List<Book> findBooks(int offset, int limit, String audio, String genre) {

        List<Book> allBooks = findAll();
        Stream<Book> bookStream = allBooks.stream();
        if (audio != null && !audio.equals("blank")) {
            bookStream = bookStream.filter(book -> book.getAudio() == Boolean.parseBoolean(audio));
        }
        if (genre != null && !genre.equals("blank")) {
            bookStream = bookStream.filter(book -> book.getGenres().stream().map(Genre::getName).collect(Collectors.toList()).contains(genre));
        }
        List<Book> books = bookStream
                .collect(Collectors.toList());

        int toIndex = Math.min(offset + limit, books.size());
        return books.subList(offset, toIndex);
    }

    @Override
    public List<String> getGenres() {
        Query query = entityManager.createNamedQuery("Genre.getGenres");
        return query.getResultList();
    }

}
