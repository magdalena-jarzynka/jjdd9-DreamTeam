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
    public void update(Rating rating) {
        entityManager.merge(rating);
    }

    @Override
    public Optional<Rating> findByBookId(Long bookId) {
        Query query = entityManager.createNamedQuery("Rating.findByBookId");
        query.setParameter("bookId", bookId);
        return query.getResultList().stream().findAny();
    }

}
