package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.cdi.RoleType;
import com.infoshareacademy.dreamteam.dao.RoleDaoBean;
import com.infoshareacademy.dreamteam.dao.UserDao;
import com.infoshareacademy.dreamteam.domain.entity.User;
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

    @EJB
    private RoleDaoBean roleDaoBean;

    public void save(User user) {
        userDao.save(user);
    }

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email).orElse(null);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRole(roleDaoBean.findByRoleType(RoleType.USER));
        save(user);
        return user;
    }

    public UserView login(UserRequest userRequest) {
        User user = userDao.findUserByEmail(userRequest.getEmail()).orElseGet(() -> createUser(userRequest));
        return userMapper.mapEntityToView(user);
    }

}