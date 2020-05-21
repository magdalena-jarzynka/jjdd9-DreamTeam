package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.dao.UserDao;
import com.infoshareacademy.dreamteam.domain.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public void remove(User user) {
        userDao.remove(user);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

}
