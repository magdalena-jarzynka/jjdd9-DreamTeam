package com.infoshareacademy.dreamteam.repository;


import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.User;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface UserRepository {

    void save(User user);

    void update(User user);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    List<User> findAll();

    List<Book> getFavourites(Long id);

    List<Book> getReservations(Long id);
}
