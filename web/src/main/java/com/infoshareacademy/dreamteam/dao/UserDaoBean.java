package com.infoshareacademy.dreamteam.dao;

import com.infoshareacademy.dreamteam.domain.User;
import com.infoshareacademy.dreamteam.storage.UserDb;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDaoBean implements UserDao {

    @Override
    public void save(User user) {
        UserDb.getRepository().add(user);
    }

    @Override
    public void remove(User user) {
        UserDb.getRepository().remove(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return UserDb.getRepository();
    }
}
