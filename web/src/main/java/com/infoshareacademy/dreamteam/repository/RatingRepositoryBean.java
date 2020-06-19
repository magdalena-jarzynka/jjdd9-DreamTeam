package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class RatingRepositoryBean implements RatingRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Rating rating) {
        entityManager.persist(rating);
    }

    @Override
    public Optional<Rating> findByBook(Book book) {
        Query query = entityManager.createNamedQuery("Rating.findByBook");
        query.setParameter("book", book);
        return query.getResultList().stream().findAny();
    }

}
