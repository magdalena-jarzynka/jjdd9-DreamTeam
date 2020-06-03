package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ReservationRepository {
    void add(Reservation reservation);

    void delete(Reservation reservation);

    List<Reservation> findReservationsByUser(User user);

    List<Reservation> findReservationsByBook(Book book);

}
