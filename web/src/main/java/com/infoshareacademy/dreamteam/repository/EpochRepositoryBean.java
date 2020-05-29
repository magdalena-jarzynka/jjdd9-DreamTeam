package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Epoch;
import com.infoshareacademy.dreamteam.domain.entity.Kind;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EpochRepositoryBean implements EpochRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save (Epoch epoch) {entityManager.persist(epoch);}

    @Override
    public Epoch update (Epoch epoch) {
        return entityManager.merge(epoch);
    }
}
