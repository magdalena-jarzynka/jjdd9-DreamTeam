package com.infoshareacademy.dreamteam.dao;


import com.infoshareacademy.dreamteam.domain.User;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface UserDao {

    void save(User user);

    void remove(User user);

    Optional<User> findByEmail(String email);

    List<User> findAll();

}
