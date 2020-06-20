package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.view.stats.AuthorStatsView;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class AuthorRepositoryBean implements AuthorRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Author author) {
        entityManager.persist(author);
    }

    @Override
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Override
    public Optional<Author> findByName(String name) {
        Query query = entityManager.createNamedQuery("Author.findAuthorByName");
        query.setParameter("name", name);
        return query.getResultList().stream().findAny();
    }

    @Override
    public List<AuthorStatsView> findAuthorReservationCount() {
        Query query = entityManager.createNamedQuery("Author.getReservationsStats");
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(Long id) {
        Query query = entityManager.createNamedQuery("Author.findAuthorById");
        query.setParameter("id", id);
        return query.getResultList().stream().findAny();
    }
}
