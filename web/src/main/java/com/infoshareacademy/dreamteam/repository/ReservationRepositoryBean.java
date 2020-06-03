package com.infoshareacademy.dreamteam.repository;


import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ReservationRepositoryBean implements ReservationRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        entityManager.remove(reservation);
    }

    @Override
    public List<Reservation> findReservationsByUser(User user) {
        Query query = entityManager.createNamedQuery("Reservation.findReservationsByUser");
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findReservationsByBook(Book book) {
        Query query = entityManager.createNamedQuery("Reservation.findReservationByBook");
        query.setParameter("book", book);
        return query.getResultList();
    }

}
