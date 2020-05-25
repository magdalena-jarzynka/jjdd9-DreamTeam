package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.cdi.Role;
import com.infoshareacademy.dreamteam.cdi.User;
import com.infoshareacademy.dreamteam.dao.UserDao;
import com.infoshareacademy.dreamteam.domain.request.UserRequest;
import com.infoshareacademy.dreamteam.domain.view.UserView;
import com.infoshareacademy.dreamteam.mapper.UserMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    @Inject
    private UserMapper userMapper;

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

    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRole(Role.USER);
        save(user);
        return findByEmail(userRequest.getEmail());
    }

    public UserView login(UserRequest userRequest) {
        User user = userDao.findByEmail(userRequest.getEmail()).orElse(createUser(userRequest));
        return userMapper.mapEntityToView(user);
    }

}
