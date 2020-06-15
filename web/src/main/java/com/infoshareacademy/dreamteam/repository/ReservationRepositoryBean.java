package com.infoshareacademy.dreamteam.repository;


import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class ReservationRepositoryBean implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public void delete(Long reservationId) {
        Reservation reservation = findReservationById(reservationId).get();
        entityManager.remove(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return entityManager.merge(reservation);
    }

    @Override
    public List<Reservation> findReservationsByUser(User user) {
        Query query = entityManager.createNamedQuery("Reservation.findReservationsByUser");
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public Optional<Reservation> findReservationRequestByUserIdAndBookId(Long userId, Long bookId) {
        Query query = entityManager.createNamedQuery("Reservation.findReservationRequestByUserIdAndBookId");
        query.setParameter("user_id", userId)
                .setParameter("book_id", bookId);
        return query.getResultList().stream().findFirst();
    }


    @Override
    public List<Reservation> findReservationsByBook(Book book) {
        Query query = entityManager.createNamedQuery("Reservation.findReservationByBook");
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
        Query query = entityManager.createNamedQuery("Reservation.findReservationById");
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<Reservation> findReservationByToken(String token) {
        Query query = entityManager.createNamedQuery("Reservation.findReservationByToken");
        query.setParameter("token", token);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Reservation> findAllReservations(){
        Query query = entityManager.createNamedQuery("Reservation.findAllReservations");
        return query.getResultList();
    }

}
