package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserRepositoryBean implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Query query = entityManager.createNamedQuery("User.findUserByEmail");
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<User> findAll() {
        Query query = entityManager.createNamedQuery("User.findAll");
        return query.getResultList();
    }

}
