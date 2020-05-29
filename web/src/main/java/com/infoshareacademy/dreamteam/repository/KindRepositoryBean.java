package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Kind;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KindRepositoryBean implements KindRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save (Kind kind) {entityManager.persist(kind);}

    @Override
    public Kind update (Kind kind) {
        return entityManager.merge(kind);
    }
}
