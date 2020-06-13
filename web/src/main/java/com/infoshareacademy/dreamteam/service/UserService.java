package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.repository.RoleRepositoryBean;
import com.infoshareacademy.dreamteam.repository.UserRepository;
import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.domain.request.UserRequest;
import com.infoshareacademy.dreamteam.domain.view.UserView;
import com.infoshareacademy.dreamteam.mapper.UserMapper;
import com.infoshareacademy.dreamteam.role.RoleType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserService {

    @EJB
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @EJB
    private RoleRepositoryBean roleRepositoryBean;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRole(roleRepositoryBean.findByRoleType(RoleType.USER).orElseThrow());
        save(user);
        return user;
    }

    public UserView login(UserRequest userRequest) {
        User user = userRepository.findUserByEmail(userRequest.getEmail()).orElseGet(() -> createUser(userRequest));
        return userMapper.mapEntityToView(user);
    }

    public List<String> getFavourites(Long id) {
        return Optional.ofNullable(userRepository.getFavourites(id)).orElse(List.of());
    }

}
